package school.sptech.sistema_estoque.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;
import school.sptech.sistema_estoque.port.ClassAppPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassAppServiceTest {
    @Mock
    private ClassAppPort classAppPort;

    @InjectMocks
    private ClassAppService service;

    @Test
    @DisplayName("Deve Listar Todas as Tags")
    void deveListarTags(){
        TagsRequest request = new TagsRequest(1, List.of());
        when(classAppPort.getTags()).thenReturn(request);
        TagsRequest resultado = service.getTags();
        assertNotNull(resultado);
        assertEquals(request.totalItems(),resultado.totalItems());
    }

    @Test
    @DisplayName("Deve Listar Todos os Groups")
    void deveListarGroups(){
        String request = "Grupos";
        when(classAppPort.getGroups()).thenReturn(request);
        String resultado = service.getGroups();
        assertNotNull(resultado);
        assertEquals(request,resultado);
    }

    @Test
    @DisplayName("Deve Listar Todos os Staffs")
    void deveListarStaffs(){
        String request = "Staffs";
        when(classAppPort.getStaffs()).thenReturn(request);
        String resultado = service.getStaffs();
        assertNotNull(resultado);
        assertEquals(request,resultado);
    }

    @Test
    @DisplayName("Deve Listar Todos os Students")
    void deveListarStudents(){
        String request = "Students";
        when(classAppPort.getStudents()).thenReturn(request);
        String resultado = service.getStudents();
        assertNotNull(resultado);
        assertEquals(request,resultado);
    }

    @Test
    @DisplayName("Deve Listar Todas as Labels")
    void deveListarLabels(){
        LabelsRequest request = new LabelsRequest(1,List.of());
        when(classAppPort.getLabels()).thenReturn(request);
        LabelsRequest resultado = service.getLabels();
        assertNotNull(resultado);
        assertEquals(request,resultado);
    }
}