package com.grupomacro.mvno.network.network.encryption.algorithm

import android.util.Log
import com.grupomacro.mvno.network.network.encryption.DefaultEncryptionConfiguration
import com.grupomacro.mvno.network.network.encryption.data.EncryptedContent
import com.grupomacro.mvno.network.network.encryption.data.EncryptionConfiguration
import java.security.SecureRandom
import java.util.Base64
import java.util.Locale
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class EncryptionUtil {

    fun encryptPKCS5(
        inputText: String,
        configuration: EncryptionConfiguration = DefaultEncryptionConfiguration.value
    ): EncryptedContent {
        val ivBytes = ByteArray(IV_SIZE_BYTES)
        val cipher = Cipher.getInstance(configuration.transformation).apply {
            val keyBytes = configuration.keyString.decodeBase64().paddingKeyTo32Bits()
            val keySpec = SecretKeySpec(keyBytes, configuration.algorithm)
            SecureRandom().nextBytes(ivBytes)
            val ivSpec = IvParameterSpec(ivBytes)
            init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        }
        val encryptedBytes = cipher.doFinal(inputText.toByteArray(charset(configuration.charset)))
        val encryptedString = appendArraysAndConvertToString(ivBytes, encryptedBytes)
        return EncryptedContent(
            iv = encryptedString.substring(startIndex = 0, endIndex = IV_SIZE_CHARS),
            encrypted = encryptedString.substring(startIndex = IV_SIZE_CHARS)
        )
    }

    fun decryptPCKS5(
        encryptedContent: EncryptedContent,
        configuration: EncryptionConfiguration = DefaultEncryptionConfiguration.value
    ): String {
        val cipherDecrypt = Cipher.getInstance(configuration.transformation).also {
            val decodedKey = configuration.keyString.decodeBase64().paddingKeyTo32Bits()
            val key = SecretKeySpec(decodedKey, 0, decodedKey.size, configuration.algorithm)
            val ivBytes = encryptedContent.iv.hexStringToByteArray()
            it.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(ivBytes))
        }
        return String(
            cipherDecrypt.doFinal(encryptedContent.encrypted.hexStringToByteArray()),
            charset(configuration.charset)
        )
    }

    private fun String.hexStringToByteArray(): ByteArray {
        val len = this.length
        val data = ByteArray(len / 2)
        try {
            var i = 0
            while (i < len) {
                val digit1 = requireNotNull(this[i].digitToIntOrNull(HEX_BASE))
                val digit2 = requireNotNull(this[i + 1].digitToIntOrNull(HEX_BASE))
                val val1 = digit1.shl(SHIFT_LEFT_CHAR)
                data[i / 2] = (val1 + digit2).toByte()
                i += 2
            }
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
        }
        return data
    }

    private fun ByteArray.paddingKeyTo32Bits(keySize: Int = KEY_SIZE): ByteArray {
        if (this.size == keySize) return this
        val completeCopies = keySize / this.size
        var accum = byteArrayOf()
        repeat(completeCopies) {
            accum += this
        }
        val remainingBytes = keySize - completeCopies * this.size
        return accum + this.take(remainingBytes)
    }

    private fun appendArraysAndConvertToString(ivBytes: ByteArray, encryptedBytes: ByteArray): String {
        return ByteArray(encryptedBytes.size + ivBytes.size).let { bufferArray ->
            System.arraycopy(ivBytes, 0, bufferArray, 0, ivBytes.size)
            System.arraycopy(encryptedBytes, 0, bufferArray, ivBytes.size, encryptedBytes.size)
            bufferArray.joinToString(separator = "") {
                String.format(Locale.getDefault(), HEX_BYTE_FORMAT, it)
            }
        }
    }

    private fun String.decodeBase64(): ByteArray {
        return Base64.getDecoder().decode(this)
    }

    companion object {
        private const val TAG = "EncryptionUtil"
        private const val IV_SIZE_BYTES = 16
        private const val IV_SIZE_CHARS = 32
        private const val KEY_SIZE = 32
        private const val SHIFT_LEFT_CHAR = 4
        private const val HEX_BASE = 16
        private const val HEX_BYTE_FORMAT = "%02x"
    }
}
