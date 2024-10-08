package com.grupomacro.mvno.database

import android.content.Context
import com.grupomacro.mvno.database.dao.SessionDao
import com.grupomacro.mvno.database.dao.UserDao
import com.grupomacro.mvno.database.di.DaggerMainDatabaseComponent
import javax.inject.Inject

class DatabaseEntryPoint private constructor() {

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var sessionDao: SessionDao

    @Inject
    lateinit var userDao: UserDao

   /* @Inject
    lateinit var notificationDao: NotificationDao

    @Inject
    lateinit var financingProductDao: FinancingProductDao

    @Inject
    lateinit var motorcycleFinancingDetailsDao: MotorcycleFinancingDetailsDao

    @Inject
    lateinit var productPaymentReferenceDao: ProductPaymentReferenceDao

    @Inject
    lateinit var productPaymentMethodDao: ProductPaymentMethodDao

    @Inject
    lateinit var productPaymentPeriodDao: ProductPaymentPeriodDao

    @Inject
    lateinit var productAmortizationDao: ProductAmortizationDao

    @Inject
    lateinit var productReceiptDao: ProductReceiptDao

    @Inject
    lateinit var bolsitaTransactionDao: BolsitaTransactionDao*/

    companion object {

        val instance = DatabaseEntryPoint()

        fun initialize(context: Context) {
            DaggerMainDatabaseComponent
                .builder()
                .context(context)
                .build()
                .inject(instance)
        }
    }
}
