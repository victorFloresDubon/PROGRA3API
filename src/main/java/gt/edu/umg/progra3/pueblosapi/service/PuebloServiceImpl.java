package gt.edu.umg.progra3.pueblosapi.service;

import com.opencsv.CSVWriter;
import gt.edu.umg.progra3.pueblosapi.dao.HabitanteDao;
import gt.edu.umg.progra3.pueblosapi.dao.PuebloDao;
import gt.edu.umg.progra3.pueblosapi.model.Pueblo;
import gt.edu.umg.progra3.pueblosapi.model.PuebloData;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class PuebloServiceImpl implements PuebloService {

    private static final String MAIN_DATASET = "habitantes.csv";
    private PuebloData puebloData;

    public PuebloServiceImpl() {
        this.puebloData = new PuebloData();
    }

    @Override
    public List<Pueblo> getPueblos() {
        return puebloData.getPueblos();
    }

    @Override
    public void crearPueblo(PuebloDao puebloDao) {
        puebloData.crearPueblo(puebloDao.getNombre());
    }

    @Override
    public void crearHabitante(HabitanteDao habitanteDao) {
        puebloData.crearHabitante(habitanteDao.getPueblo(), habitanteDao.getHabitante());
    }

    @Override
    public void guardarTodos(List<Pueblo> pueblos) {
        // Instanciamos la clase CSVWriter
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(MAIN_DATASET));
            // Escribimos la información al set de datos
            for(Pueblo pueblo : pueblos){
                for(String habitante : pueblo.getArbolAvl().getKeys()){
                    String data = String.format("%s,%s", pueblo.getNombre(), habitante);
                    writer.writeNext(data.split(","));
                }
            }
            // Vaciamos los datos del escritor de archivo
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminarPuebloPorId(String id) {

    }

    @Override
    public boolean isPuebloCreado(String id) {
        return puebloData.isPuebloExistente(id);
    }
}
