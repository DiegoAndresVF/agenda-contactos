import java.util.ArrayList;
import java.util.List;

public class Agenda {

  private List<Contacto> contactos;

  public Agenda(){
    this.contactos = new ArrayList<>();
  }

  void anadirContacto (Contacto contacto) {
    if(contactos.size() >= 10) {
      System.out.println("> Agenda llena");
    } else {
      this.contactos.add(contacto);
      System.out.println("> Contacto añadido");
    }
  }

  boolean existeContacto(Contacto c) {
    /*System.out.println(contactos.contains(c));*/
    return contactos.contains(c);
  }

  void listarContacto() {
    for (Contacto contacto : contactos) {
      System.out.println("nombre: " + contacto.getNombre() +  "\napellido: " + contacto.getApellido() + "\ntelefono: " + contacto.getTelefono());
    }
  }

  String buscarContacto(String nombre) {
    Contacto contacto = this.contactos.stream().filter(c -> c.getNombre().equals(nombre)).findFirst().orElse(null);
    if(existeContacto(contacto)) {
      System.out.println("> El telefono de contacto es: " + contacto.getTelefono());
      return contacto.getTelefono();
    } else {
      return "> El contacto no existe";
    }
  }

  Contacto buscarContactoEliminar(String nombre){
    return this.contactos.stream().filter(c -> c.getNombre().equals(nombre)).findFirst().orElse(null);
  }

  void eliminarContacto(Contacto deleteContact) {
    if (this.existeContacto(deleteContact)) {
      this.contactos.remove(deleteContact);
      System.out.println("> Contacto eliminado");
    }
  }

  void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
    this.contactos.stream()
        .filter(c -> nombre.equalsIgnoreCase(c.getNombre()) &&
            apellido.equalsIgnoreCase(c.getApellido()))
        .findFirst()
        .ifPresentOrElse(
            c -> {
              c.setTelefono(nuevoTelefono);
              System.out.println("Teléfono actualizado a " + c.getTelefono());
            },
            () -> System.out.println("Contacto no encontrado")
        );
  }

  void agendaLlena() {
    if (contactos.size() >= 10) {
      System.out.println("Agenda llena");
    } else {
      System.out.println("Agenda Disponible");
    }
  }

  void espaciosLibres() {
    int freeSpace = 10 - contactos.size();
    if (freeSpace > 0) {
      System.out.println("Espacios disponibles: " + freeSpace);
    }

  }
}
