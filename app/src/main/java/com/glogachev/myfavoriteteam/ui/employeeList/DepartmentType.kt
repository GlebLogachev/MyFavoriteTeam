package com.glogachev.myfavoriteteam.ui.employeeList

import com.glogachev.myfavoriteteam.R

enum class DepartmentType(val nameResId: Int) {
    ALL(R.string.all_departments_name),
    ANDROID(R.string.android_name),
    IOS(R.string.ios_name),
    DESIGN(R.string.design_name),
    MANAGEMENT(R.string.management_name),
    QA(R.string.qa_name),
    BACK_OFFICE(R.string.back_office_name),
    FRONTEND(R.string.frontend_name),
    HR(R.string.hr_name),
    PR(R.string.pr_name),
    BACKEND(R.string.backend_name),
    SUPPORT(R.string.support_name),
    ANALYTICS(R.string.analytics_name)
}