package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.AdminService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EmployeeService;
import org.springframework.samples.petclinic.service.EmailService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@WebMvcTest(controllers = AdminController.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration= SecurityConfiguration.class)
public class AdminControllerMockTest {
    private static final int TEST_ADMIN_ID = 1;
    public static final String ADMIN_HOME  = "admin/adminHome";
    public static final String ADMIN_EMAIL = "admin/newEmail";
    public static final String ADMIN_ANNOUNCEMENT = "admin/newAnnouncement";
    public static final String ADMIN_USERS = "admin/checkUsers";
    public static final String ADMINS_FORM ="admin/createOrUpdateAdminsForm";
    public static final String ADMIN_BDAYS ="admin/checkBirthdays";
    @MockBean
    private AdminService adminService;

    @MockBean
    private UserService userService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    private Admin admin;
    private Admin aAux;
    private User u;
    private User uAdmin;
    private Collection<User> users;
    private Collection<Admin> admins;
    private Optional<Admin> aOptional;
    private Optional<User> uOptional;
    private Optional<User> uOptionalAdmin;

    @BeforeEach
    public void setUp(){
       /*Authorities Cliente*/
       Authorities a = new Authorities();
       Set<Authorities> aSet = new HashSet<Authorities>();
       a.setAuthority("client");
       aSet.add(a);

        /*Usuario*/
        u = new User("miguel","hola",false, aSet);
        uOptional = Optional.of(u);
        users = new ArrayList<User>();
        users.add(u);
                
        /*Authorities Admin*/
        Authorities aD = new Authorities();
        Set<Authorities> aSetD = new HashSet<Authorities>();
        aD.setAuthority("admin");
        aSetD.add(aD);


        /*Admin*/
        admin = new Admin();
        uAdmin = new User("admin","hola", true, aSetD);
        admin.setId(1);
        admin.setUser(uAdmin);
        uOptionalAdmin = Optional.of(uAdmin);

        /*Admins*/
        admins = new ArrayList<Admin>();
        admins.add(admin);
        admins.add(aAux);

        given(this.adminService.findById(TEST_ADMIN_ID)).willReturn(aOptional);

        given(this.userService.findUser("admin")).willReturn(uOptionalAdmin);
        given(this.userService.findUser("miguel")).willReturn(uOptional);
    }

    @WithMockUser(value = "spring")
    @Test
    public void getNewAdminFormSuccess() throws Exception{
        mockMvc.perform(get("/admin/new")).andExpect(status().isOk())
            .andExpect(model().attributeExists("admin"))
            .andExpect(model().attributeExists("user"))
            .andExpect(view().name(ADMINS_FORM));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewAdminFormSuccess() throws Exception {
        mockMvc.perform(post("/admin/new")
            .with(csrf())
            .param("user.username", "eljefazo")
            .param("user.password", "hola"))
            .andExpect(status().isOk());
    }

    @WithMockUser(value = "admin")
    @Test
    public void showUsersDetails() throws Exception {
        mockMvc.perform(get("/admin/users"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("clientUsers"))
            .andExpect(model().attributeExists("employeeUsers"))
            .andExpect(view().name(ADMIN_USERS));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getDeleteUserSuccess() throws Exception{
        mockMvc.perform(get("/admin/users/{username}/delete", "miguel"))
            .andExpect(model().attribute("message","User deleted succesfully"))
            .andExpect(status().isOk())
            .andExpect(view().name(ADMIN_USERS));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getDeleteUserNotExist() throws Exception{
        mockMvc.perform(get("/admin/users/{username}/delete", "juanma"))
            .andExpect(model().attribute("message","Ups that username doesnt exist, there must be a problem"))
            .andExpect(status().isOk())
            .andExpect(view().name(ADMIN_USERS));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getEnableUserSuccess() throws Exception{
        mockMvc.perform(get("/admin/users/{username}/turn_on", "miguel"))
            .andExpect(model().attribute("message",String.format("State of user %s has been changed", "miguel")))
            .andExpect(status().isOk())
            .andExpect(view().name(ADMIN_USERS));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getEnableUserNotExist() throws Exception{
        mockMvc.perform(get("/admin/users/{username}/turn_on", "juanma"))
            .andExpect(model().attribute("message","Ups that username doesnt exist, there must be a problem"))
            .andExpect(status().isOk())
            .andExpect(view().name(ADMIN_USERS));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewEmailSuccess() throws Exception{
        mockMvc.perform(post("/admin/newEmail")
            .with(csrf())
            .param("address", "everybodyebola@gmail.com")
            .param("subject", "prueba1")
            .param("body", "prueba1"))
            .andExpect(status().isOk())
            .andExpect(view().name(ADMIN_HOME));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewAnnouncementSuccess() throws Exception{
        mockMvc.perform(post("/admin/newAnnouncement")
            .with(csrf())
            .param("address", "ALL")
            .param("subject", "NEWS LETTER MINERAL HOUSE SPA")
            .param("body", "prueba"))
            .andExpect(status().isOk())
            .andExpect(view().name(ADMIN_HOME));
    }

    @WithMockUser(value="admin")
    @Test
    public void postBirthdaysSuccess() throws Exception {
        mockMvc.perform(post("/admin/birthdays")
        .with(csrf())
        .param("month", "JANUARY"))
        .andExpect(model().attributeExists("clientBdays"))
        .andExpect(model().attributeExists("employeeBdays"))
        .andExpect(status().isOk())
        .andExpect(view().name(ADMIN_BDAYS));
    }
}