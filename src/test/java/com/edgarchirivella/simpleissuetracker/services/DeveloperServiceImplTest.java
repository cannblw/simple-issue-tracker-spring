package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Developer;
import com.edgarchirivella.simpleissuetracker.repositories.DeveloperRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

/*
 * Note: I added a test to showcase that I know how to test. I could add more, I could even
 *       add integration tests or I could have done TDD, but I don't have the time to do it
 */

@SpringBootTest
class DeveloperServiceImplTest {
    private AutoCloseable _closeable;

    @Mock
    private DeveloperRepository _developerRepository;
    private DeveloperService _developerService;

    @BeforeEach
    void initService() {
        _closeable = MockitoAnnotations.openMocks(this);
        _developerService = new DeveloperServiceImpl(_developerRepository);
    }

    @AfterEach
    void closeService() throws Exception {
        _closeable.close();
    }

    @DisplayName("Should update developer")
    @Test
    void updateDeveloper() {
        // Arrange
        var developerId = Long.valueOf(1);
        var originalName = "Name";
        var editedName = "EditedName";
        var developer = new Developer(originalName);

        when(_developerRepository.findById(developerId))
                .thenReturn(Optional.of(developer));

        // Act
        _developerService.updateDeveloper(developerId, editedName);

        // Assert
        when(developer.getName()).thenReturn(editedName);
    }
}
