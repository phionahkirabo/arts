package com.rra.arts.arts_backend.seeders;

import com.rra.arts.arts_backend.model.Department;
import com.rra.arts.arts_backend.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentSeeder implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) {

        seedDepartment("CGO", "Commissioner General Office");
        seedDepartment("DTD", "Domestic Taxes Department");
        seedDepartment("CSD", "Customs Services Department");
        seedDepartment("FIND", "Finance Department");
        seedDepartment("ITDTD", "Information Technology & Digital Transformation Department");
        seedDepartment("LSBAD", "Legal Services and Board Affairs Department");
        seedDepartment("SRAD", "Strategy and Risk Analysis Department");
        seedDepartment("IAID", "Internal Audit and Integrity Department");
        seedDepartment("ALD", "Administration and Logistics Division");
        seedDepartment("HR", "Human Resource");
        seedDepartment("SIID", "Strategic Intelligence and Investigation Division");
        seedDepartment("TPS", "Taxpayer Service and Communication Division");
        seedDepartment("TAD", "Taxpayer Audit Division");
        seedDepartment("PDRD", "Provincial and Decentralized Revenue Division");
        seedDepartment("DMD", "Debt Management Division");
        seedDepartment("RFPD", "Registration, Filing and Payment Division");
        seedDepartment("TCOSD", "Tax Control and Operational Support Division");
        seedDepartment("COD", "Customs Operations Division");
        seedDepartment("COSD", "Customs Operations Support Division");
        seedDepartment("SPIU", "Single Project Implementation Unit");
        seedDepartment("CRADAD", "Compliance Risk Analysis and Data Analytics Division");
        seedDepartment("PRSD", "Planning, Research and Statistics Division");
        seedDepartment("RAD", "Revenue Accounting Division");
    }

    private void seedDepartment(String code, String name) {

        if (!departmentRepository.existsByCode(code)) {

            Department department = Department.builder()
                    .code(code)
                    .name(name)
                    .description(name)
                    .build();

            departmentRepository.save(department);

            System.out.println("Department seeded: " + name);
        }
    }
}