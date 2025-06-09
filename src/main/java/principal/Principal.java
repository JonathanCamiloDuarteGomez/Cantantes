package principal;

import com.camilo.cantantes.cantantes.Repository.MusicaRepository;
import com.camilo.cantantes.cantantes.model.Cancion;
import com.camilo.cantantes.cantantes.model.Cantante;

import java.text.DateFormat;
import java.util.*;

public class Principal {
    private Scanner teclado =new Scanner(System.in);
    private MusicaRepository repository;
    private Cantante cantante;

    public Principal( MusicaRepository repo){
        this.repository=repo;
    }

    public void verMenu(){
        var opcion = -1;

        while(opcion!=0){


            var menu = """
                \n
                <-***** MENU *****->
                1 - Registrar Cantantes
                2 - Ver lista de Cantes
                3 - Registrar Datos de Canciones
                4 - Buscar Canciones Por Cantante
                
                0 - Salir 
                """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    registrarCantantes();
                    break;
                case 2:
                    repository.obtenerNombresDeCantantes().forEach(System.out::println);
                    break;
                case 3:
                    registrarDatosCanciones();
                    break;
                case 4:
                    buscarCancionesPorCantante();
                    break;
                case 0:
                    System.out.println("Cerrando la app");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }

    }

    private void registrarCantantes() {

        System.out.println("Ingrese los siguientes datos en orden: ");
        System.out.println("Id Cantante: ");
        var id_cantante = teclado.nextInt();
        teclado.nextLine();
        Optional<Cantante> busquedaDeIdRepetidos = repository.findByIdCantante(id_cantante);
        if(!busquedaDeIdRepetidos.isPresent()){
            System.out.println("Ingrese el nombre del cantante: ");
            var nombre = teclado.nextLine();
            System.out.println("Ingrese el pais de origen : ");
            var pais_origen = teclado.nextLine();
            System.out.println("Ingrese el genero_musical: ");
            var genero_musical = teclado.nextLine();
            System.out.println("¿El cantante está activo? si/no: ");
            String activo = teclado.nextLine().toLowerCase();
            boolean estaActivo = activo.equals("si");
            cantante = new Cantante(id_cantante,nombre.toLowerCase(),pais_origen,genero_musical,estaActivo);
            System.out.println(cantante.toString());
            repository.save(cantante);
        }else{
            System.out.println("El numero de identificacion"+id_cantante+ "ya se encuentra registrado en el sistema");
        }

    }
    private void registrarDatosCanciones() {

        System.out.println("Ingrese el nombre del cantante ");
        var nombreCantanteCapturado = teclado.nextLine();

        Optional<Cantante> busquedaCantanteBd = repository.findByNombre(nombreCantanteCapturado); // usando JPQL

        if (busquedaCantanteBd.isPresent()) {
            var cantante = busquedaCantanteBd.get();

            System.out.println("Ingrese el titulo de la cancion: ");
            var tituloCancion = teclado.nextLine();

            System.out.println("Ingrese duracion de la cancion: 1.25 min");
            String entrada = teclado.nextLine().trim().replace(",", ".");
            Float duracion = Float.parseFloat(entrada);


            System.out.println("Ingrese el genero de la cancion: ");
            var genero = teclado.nextLine();

            Cancion cancion = new Cancion(tituloCancion, String.valueOf(duracion), genero);

            // Asocia la canción al cantante
            cancion.setCantante(cantante);

            // Agrega la canción a la lista de canciones del cantante
            cantante.getCanciones().add(cancion);

            // Guarda el cantante con su nueva canción (esto funciona si hay Cascade.PERSIST o Cascade.ALL)
            repository.save(cantante);

        } else {
            System.out.println("No se ha encontrado el cantante, por tanto no se puede crear la canción.");
        }
    }
    private void buscarCancionesPorCantante(){
        System.out.println("Ingrese el nombre del cantante ");
        String nombreCantanteCapturado = teclado.nextLine().trim();
        nombreCantanteCapturado = nombreCantanteCapturado.substring(0, 1).toUpperCase() + nombreCantanteCapturado.substring(1).toLowerCase();

        Optional<Cantante> busquedaCantanteBd = repository.findByNombre(nombreCantanteCapturado);
        if(busquedaCantanteBd.isPresent()){
            List<Object[]> resultados = repository.cancionesDelAutor(nombreCantanteCapturado);
            for (Object[] fila : resultados) {
                System.out.println("Nombre: " + fila[0] + ", Título: " + fila[1]);
            }
        }
    }

}
