package org.springframework.samples.petclinic.service;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.AdminRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class AdminMockTest {
    @Mock
    private AdminRepository adminRepository;

    @Mock
    private AuthoritiesService authoritiesService;

    @Mock
    private UserService userService;

    private AdminService adminService;
    private Admin a;
    private User u;
    private Collection<Admin> admins;
    private Optional<Admin> aOptional;

    @Before
    public void setUp(){
        adminService = new AdminService(adminRepository, userService, authoritiesService);
        a = new Admin();
        u = new User();
        admins = new ArrayList<Admin>();

        u.setUsername("eljefazo");
        u.setPassword("correcthorsebatterystaple");
        u.setEnabled(true);
        a.setId(1);
        a.setUser(u);
        admins.add(a);

        aOptional = Optional.of(a);

        when(adminRepository.findAll()).thenReturn(admins);
        when(adminRepository.findById(1)).thenReturn(aOptional);
        when(adminRepository.save(a)).thenReturn(a);
    }

    @Test
    public void shouldFindAll(){
        Collection<Admin> adminsExample = this.adminRepository.findAll();

        assertThat(adminsExample).hasSize(1);
        assertThat(adminsExample.iterator().next().getUser().getUsername()).isEqualTo("eljefazo");
    }

    @Test
    public void shouldFindById(){
        Optional<Admin> a1 = adminRepository.findById(1);
        assertTrue(a1.isPresent());

        Optional<Admin> a2 = adminRepository.findById(10);
        assertFalse(a2.isPresent());
    }

    @Test
    public void shouldSave(){
        adminService.save(a);
        verify(userService).saveUser(a.getUser());
    }

    @Test
    public void shouldDeleteAdmin(){
        adminService.delete(a);
        verify(userService).delete(a.getUser());
    }
}
