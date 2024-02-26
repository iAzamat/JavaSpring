package ru.gb.homework10.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.gb.homework10.database.entity.Session;
import ru.gb.homework10.database.entity.User;
import ru.gb.homework10.database.repository.SessionRepository;
import ru.gb.homework10.database.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Проверка, что возвращается сессия
     */
    @Test
    void loginValidCredentialsReturnsSession() {
        String username = "testUser";
        String password = "testPassword";
        User user = new User(username, passwordEncoder.encode(password));
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

        Session session = authService.login(username, password);

        assertNotNull(session);
        verify(sessionRepository).save(any(Session.class));
    }

    /**
     * Проверка, что передан неверный пароль
     */
    @Test
    void loginInvalidCredentialsReturnsNull() {
        String username = "testUser";
        String password = "testPassword";
        User user = new User(username, passwordEncoder.encode(password));
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);

        Session session = authService.login(username, password);

        assertNull(session);
        verify(sessionRepository, never()).save(any(Session.class));
    }

    /**
     * Проверка на то, что пользователя не существует
     */
    @Test
    void loginUserNotFoundReturnsNull() {
        String username = "testUser";
        String password = "testPassword";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        Session session = authService.login(username, password);

        assertNull(session);
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    void registerUserReturnsUsername() {
        User user = new User("testUser", "testPassword");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        String username = authService.register(user);

        assertEquals(user.getUsername(), username);
        verify(userRepository).save(user);
    }

    @Test
    void registerExistingUserThrowsException() {
        User user = new User("testUser", "testPassword");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class, () -> authService.register(user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void logoutUserExistsSessionDeleted() {
        Long userId = 1L;
        User user = new User("testUser", "testPassword");
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(sessionRepository.findByUserId(userId)).thenReturn(Optional.of(new Session()));

        authService.logout(userId);

        verify(sessionRepository).deleteById(any());
    }

    @Test
    void logoutUserDoesNotExistNoSessionDeleted() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        authService.logout(userId);

        verify(sessionRepository, never()).deleteById(any());
    }
}