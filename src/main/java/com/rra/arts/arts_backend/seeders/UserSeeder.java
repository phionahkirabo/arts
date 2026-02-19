package com.rra.arts.arts_backend.seeders;



import com.rra.arts.arts_backend.model.Department;
import com.rra.arts.arts_backend.model.User;
import com.rra.arts.arts_backend.model.enums.UserRole;
import com.rra.arts.arts_backend.repository.DepartmentRepository;
import com.rra.arts.arts_backend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {

    private final UsersRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        Department iaid = departmentRepository.findByCode("IAID")
                .orElseThrow(() -> new RuntimeException("IAID department not found"));

        Department dtd = departmentRepository.findByCode("DTD")
                .orElseThrow(() -> new RuntimeException("DTD department not found"));

        // SYSTEM ADMIN
        seedUser(
                "System Administrator",
                "admin@rra.gov",
                "0780000000",
                "Admin@123",
                UserRole.SYSTEM_ADMIN,
                iaid
        );

        // INTERNAL AUDITOR
        seedUser(
                "Internal Auditor",
                "auditor@rra.gov",
                "0780000001",
                "Password@123",
                UserRole.INTERNAL_AUDITOR,
                iaid
        );

        // DIRECTOR
        seedUser(
                "Director IAU",
                "director@rra.gov",
                "0780000002",
                "Password@123",
                UserRole.DIRECTOR_IAU,
                iaid
        );

        // FOCAL PERSON
        seedUser(
                "Focal Person DTD",
                "focal.dtd@rra.gov",
                "0780000003",
                "Password@123",
                UserRole.FOCAL_PERSON,
                dtd
        );
    }

    private void seedUser(
            String fullName,
            String email,
            String phone,
            String rawPassword,
            UserRole role,
            Department department
    ) {

        if (!userRepository.existsByEmail(email)) {

            User user = new User();
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRole(role);
            user.setDepartment(department);
            user.setIsActive(true);

            userRepository.save(user);

            System.out.println("User seeded: " + email);
        }
    }
}