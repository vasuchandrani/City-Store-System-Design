package com.cityshop.cityshopbackend.security.jwt;

import com.cityshop.cityshopbackend.customer.CustomerRepository;
import com.cityshop.cityshopbackend.deliverer.DelivererRepository;
import com.cityshop.cityshopbackend.shopkeeper.ShopkeeperRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.jspecify.annotations.NullMarked;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final ShopkeeperRepository shopkeeperRepository;
    private final DelivererRepository delivererRepository;

    public CustomUserDetailsService(
            CustomerRepository customerRepository,
            ShopkeeperRepository shopkeeperRepository,
            DelivererRepository delivererRepository
    ) {
        this.customerRepository = customerRepository;
        this.shopkeeperRepository = shopkeeperRepository;
        this.delivererRepository = delivererRepository;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String compositeUsername) throws UsernameNotFoundException {

        // extract role from: "ROLE:user@email.com"
        if (!compositeUsername.contains(":")) {
            throw new UsernameNotFoundException("Invalid login format");
        }

        String[] parts = compositeUsername.split(":", 2);
        String role  = parts[0];
        String email = parts[1];

        return switch (role) {
            case "CUSTOMER" -> customerRepository.findByEmail(email)
                    .map(s -> new CustomUserDetails(s.getEmail(), s.getPassword(), "STUDENT"))
                    .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

            case "SHOPKEEPER" -> shopkeeperRepository.findByEmail(email)
                    .map(s -> new CustomUserDetails(s.getEmail(), s.getPassword(), "STUDENT"))
                    .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

            case "DELIVERER" -> delivererRepository.findByEmail(email)
                    .map(s -> new CustomUserDetails(s.getEmail(), s.getPassword(), "STUDENT"))
                    .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

            default -> throw new IllegalStateException("Unexpected value: " + role);
        };
    }
}