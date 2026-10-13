package esercizio2skeletonultithread;

public class ImpiegatoServer implements Impiegato {
    private String nome;
    private String ID;
    private int stipendio;

    public ImpiegatoServer(String n, String i, int s) {
        this.nome = n;
        this.ID = i;
        this.stipendio = s;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public int getStipendio() {
        return stipendio;
    }

    @Override
    public int aumentaStipendio(int diQuanto) {
        if (diQuanto > 0) {
            stipendio += diQuanto;
        }
        return stipendio;
    }
}
