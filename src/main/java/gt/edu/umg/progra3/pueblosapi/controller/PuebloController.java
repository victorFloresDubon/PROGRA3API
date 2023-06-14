package gt.edu.umg.progra3.pueblosapi.controller;

import gt.edu.umg.progra3.pueblosapi.dao.HabitanteDao;
import gt.edu.umg.progra3.pueblosapi.dao.PuebloDao;
import gt.edu.umg.progra3.pueblosapi.model.Mensaje;
import gt.edu.umg.progra3.pueblosapi.model.Pueblo;
import gt.edu.umg.progra3.pueblosapi.service.PuebloService;
import gt.edu.umg.progra3.pueblosapi.service.PuebloServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pueblo", description = "Información sobre los pueblos")
@Controller
@RequestMapping("/pueblos")
public class PuebloController {

    PuebloService puebloService;

    public PuebloController(){
        puebloService = new PuebloServiceImpl();
    }

    /**
     * Lista todos los pueblos registrados
     * @return
     */
    @Operation(
            summary = "Listar todos los pueblos",
            description = "Obtenemos una lista de los nombres de los pueblos registrados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Exitoso",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Pueblo.class
                                                    )
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontraron datos",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = Mensaje.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Servicio no disponible"
                    )
            }

    )
    @GetMapping("/")
    public ResponseEntity<List<Pueblo>> getPueblos(){
        List<Pueblo> list = puebloService.getPueblos();
        if(list.isEmpty()){
            return new ResponseEntity(new Mensaje("No se encontraron pueblos registrados"), HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
    }

    @Operation(
            summary = "Crear un pueblo",
            description = "Creamos un nuevo pueblo a partir de un nombre brindado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pueblo creado con éxito",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Mensaje.class),
                                            examples = @ExampleObject(
                                                    name = "Pueblo-200",
                                                    value = "{\"mensaje\": \"Pueblo creado!\"}"
                                            )
                                    )
                            }
                    )
            }
    )
    @PostMapping("/crear-pueblo")
    public ResponseEntity<?> crearPrueblo(@RequestBody PuebloDao puebloDao){
        puebloService.crearPueblo(puebloDao);
        return new ResponseEntity<>(new Mensaje("Pueblo creado!"), HttpStatusCode.valueOf(200));
    }

    @Operation(
            summary = "Crear un habitante",
            description = "Creamos un nuevo habitante dentro de un pueblo",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Habitante creado con éxito",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Mensaje.class),
                                            examples = @ExampleObject(
                                                    name = "Pueblo-200",
                                                    value = "{\"mensaje\": \"Habitante creado!\"}"
                                            )
                                    )
                            }
                    )
            }
    )
    @PostMapping("/crear-habitante")
    public ResponseEntity<?> crearHabitante(@RequestBody HabitanteDao habitanteDao){
        // Verificamos que exista el pueblo creado
        if(puebloService.isPuebloCreado(habitanteDao.getPueblo()))
            return new ResponseEntity<>(new Mensaje("No se puede agregar un habitante a un pueblo no existente"), HttpStatusCode.valueOf(400));
        puebloService.crearHabitante(habitanteDao);
        return new ResponseEntity<>(new Mensaje("Habitante creado!"), HttpStatusCode.valueOf(200));
    }

    @Operation(
            summary = "Eliminar un habitante",
            description = "Eliminación de un pueblo con todas sus habitantes",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pueblo eliminado exitosamente",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Mensaje.class),
                                            examples = @ExampleObject(
                                                    name = "Pueblo-200",
                                                    value = "{\"mensaje\": \"Pueblo eliminado exitosamente!\"}"
                                            )
                                    )
                            }
                    )
            }
    )
    @DeleteMapping("/eliminarHabitante")
    public ResponseEntity<?> eliminarHabitante(@RequestParam(name = "pueblo") String id){
        puebloService.eliminarPuebloPorId(id);
        return new ResponseEntity<>(new Mensaje("Pueblo eliminado exitosamente!"), HttpStatusCode.valueOf(200));
    }

    @Operation(
            summary = "Eliminar un pueblo",
            description = "Eliminación de un pueblo con todas sus habitantes",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pueblo eliminado exitosamente",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Mensaje.class),
                                            examples = @ExampleObject(
                                                    name = "Pueblo-200",
                                                    value = "{\"mensaje\": \"Pueblo eliminado exitosamente!\"}"
                                            )
                                    )
                            }
                    )
            }
    )
    @DeleteMapping("/eliminarPueblo")
    public ResponseEntity<?> eliminarPuebloPorId(@RequestParam(name = "pueblo") String id){
        puebloService.eliminarPuebloPorId(id);
        return new ResponseEntity<>(new Mensaje("Pueblo eliminado exitosamente!"), HttpStatusCode.valueOf(200));
    }

}