import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Agenda agenda1 = new Agenda();
    //Contacto contacto1 =  new Contacto("diego", "villagran", "12345678");
    Scanner sc = new Scanner(System.in);
    int op;
    String nombre, apellido, telefono;

    agenda1.espaciosLibres();

    do{
      System.out.println("\n--- AGENDA TELEFÓNICA ---");
      System.out.println("1. Añadir contacto");
      System.out.println("2. Listar contactos");
      System.out.println("3. Buscar contacto");
      System.out.println("4. Eliminar contacto");
      System.out.println("5. Modificar teléfono");
      System.out.println("6. Ver espacios libres");
      System.out.println("0. Salir");
      System.out.print("Ingrese número de opción: ");
      op = sc.nextInt();
      sc.nextLine();

      switch (op){
        case 1 -> {
          System.out.print("Ingrese nombre: ");
          nombre = sc.next();
          System.out.print("Ingrese apellido: ");
          apellido = sc.next();
          System.out.print("Ingrese teléfono: ");
          telefono = sc.next();

          Contacto contacto1 = new Contacto(nombre, apellido, telefono);
          agenda1.anadirContacto(contacto1);
        }
        case 2 -> agenda1.listarContacto();
        case 3 -> {
          System.out.print("Ingrese nombre a buscar: ");
          nombre = sc.next();
          agenda1.buscarContacto(nombre);
        }
        case 4 -> {
          System.out.print("Ingrese nombre del contacto a eliminar: ");
          nombre = sc.next();
          Contacto contactoEliminar = agenda1.buscarContactoEliminar(nombre);
          agenda1.eliminarContacto(contactoEliminar);
        }
        case 5 -> {
          System.out.print("Igrese nombre: ");
          nombre = sc.next();
          System.out.print("Ingrese apellido: ");
          apellido = sc.next();
          System.out.print("Ingrese nuevo número de teléfono: ");
          telefono = sc.next();
          agenda1.modificarTelefono(nombre,apellido,telefono);
        }
        case 6 -> {
          agenda1.espaciosLibres();
        }
      }
    }while(op!=0);
  }
}