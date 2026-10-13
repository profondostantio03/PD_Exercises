package esercizio2skeletonultithread;

public class Client {
    public static void main(String[] args) {
        try {
            // args[0] conterrà l'host (es. "localhost")
            String host = (args.length > 0) ? args[0] : "localhost";
            
            Impiegato imp = new Impiegato_Stub(host);

            System.out.println("Nome: " + imp.getNome());
            System.out.println("ID: " + imp.getID());
            System.out.println("Stipendio: " + imp.getStipendio());
            
            System.out.println("Aumentiamo lo stipendio di 1000 euro...");
            System.out.println("Ora il suo stipendio è di: " + imp.aumentaStipendio(1000));

            ((Impiegato_Stub) imp).close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
