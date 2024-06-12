import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessaSalario {

    static Scanner sn = new Scanner(System.in);


    static void insertFuncionario(List<Funcionario> listaFuncionarios){
        System.out.println("Introduza o nome do funcionário: ");
        String nome = sn.next();

        System.out.println("Introduza o salario diario: ");
        double salarioDia = sn.nextDouble();

        System.out.println("Introduza o numero de dias trabalhados: ");
        int diasTrabalho = sn.nextInt();

        Funcionario funcionario = new Funcionario(nome,salarioDia,diasTrabalho);
        listaFuncionarios.add(funcionario);

        sn.nextLine();
    }


    public static void main(String[] args) {

        List<Funcionario> listaFuncionarios = new ArrayList<>();

       /* */ Funcionario f1 = new Funcionario("Alberto", 65.0,29);
        listaFuncionarios.add(f1);
        Funcionario f2 = new Funcionario("Dalila", 47.8,30);
        listaFuncionarios.add(f2);


        //2-a)
        boolean comp = f1.comparacao(f2);

        System.out.println("\nnumero de insercoes");
        int qtd = sn.nextInt();

        for (int i = 0; i<qtd;  i++){
            insertFuncionario(listaFuncionarios);
        }


        //2-b)
        for (Funcionario funcionario: listaFuncionarios){
            System.out.println(funcionario.toString());
        }

        //2-e)
        for (Funcionario funcionario: listaFuncionarios){
            double salarioTotal = Funcionario.calcSalario(funcionario.getSalarioDiario(), funcionario.getDiasTrabalho());
            System.out.println("\nNome: " + funcionario.getNome()+
                    "\nSalario Total " + salarioTotal);
        }

        //2-d)
        int qtdInstancias = Funcionario.qtdInstancias;
        System.out.println("\nNumero de instancias criadas = " + qtdInstancias);

    }
}