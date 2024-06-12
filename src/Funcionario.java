public class Funcionario {



    private int Codigo;
    private String Nome;
    private double SalarioDiario;
    private int DiasTrabalho;

    //c)
    public static int qtdInstancias;


    Funcionario(String Nome, double SalarioDiario, int DiasTrabalho){
        this.Nome = Nome;
        this.SalarioDiario = SalarioDiario;
        this.DiasTrabalho = DiasTrabalho;
        qtdInstancias++;
    }

    public Funcionario(int codigo, String nome, int diasTrabalhados, double salarioDiaro, double salarioMensal) {
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }
    public String getNome() {
        return Nome;
    }

    public double getSalarioDiario() {
        return SalarioDiario;
    }


    public int getDiasTrabalho() {
        return DiasTrabalho;
    }


    public boolean comparacao(Funcionario obj) {
        if (this.Nome == obj.Nome){
            System.out.println("\nOs Funcionarios "+this.Nome +" e "+obj.Nome + " são iguais.");
            return true;
        } else {
            System.out.println("\nOs Funcionarios "+this.Nome +" e "+obj.Nome + " são diferentes.");
            return false;
        }
    }

    //b)
    public static double calcSalario(double SalarioDiario, int dias){
        return (SalarioDiario * dias);
    }

    public double calcSalario(){
        double salarioTotal = (SalarioDiario * DiasTrabalho);
        return salarioTotal;
    }

    public String toString(){
        String descricao = "Nome " + Nome +
                "\nSalario diario " + SalarioDiario +
                "\nDias Trabalhados " + DiasTrabalho +
                "\nSalario Mensal " + calcSalario(SalarioDiario,DiasTrabalho);
        return descricao;
    }
}
