package com.demo.controller

import com.demo.repository.employee.SalariesRepository
import com.demo.repository.employee.dto.EmployeeInfo
import com.demo.repository.employee.entity.SalariesEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/kotlin")
class KotlinController {

    @Autowired
    lateinit var salariesRepository: SalariesRepository

    @GetMapping("/getSalaryByAmount")
    fun getSalaryByAmount(@RequestBody params : ModelMap): List<Any?> {
//        val data = salariesRepository.getEmployeeSalary(params["amount"] as Int)
        return salariesRepository.findAllById(listOf(253406))
//        val email = values["email"] ?: throw IllegalStateException("Email is missing!")
//        return data
//                .filter { s -> s.salary > 38623 }
//                .map { s -> s.toEmployeeView() }
//                .map { s -> EmployeeView(name = s.fullName, salary = s.salary) }
    }

    private fun EmployeeInfo.toEmployeeView() = EmployeeView(
            name = "$fullName - $empNo",
            salary = salary
    )
    data class EmployeeView(
            val name: String,
            val salary: Int
    )
}

fun main() {
//    val b: String? = null
//    println(b?.length)

    val rectangle = SalariesEntity(
        salary = 11,
        currentPage = 1,
        empNo = 2
    )
    rectangle.salary = 10
    println("The perimeter is ${rectangle.toString()}")
}

class Test(
    val name: String,
) {
    var stringRepresentation: String = ""
        get() = this.toString()
        set(value) {field = value}
}
