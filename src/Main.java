import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{


    public static void main( String [] args) throws FileNotFoundException
    {
        Scanner odczyt = new Scanner(new File("C:/Users/Natalka/IdeaProjects/CheckersT/src/test.txt"));
        String linia = "";
        int licznik =0;
        while(odczyt.hasNext())
        {
            linia = odczyt.nextLine();
            String [] wartosci = linia.split(" ");
            int wartosciInt [] = new int [4];

                for(int i = 0 ; i < wartosci.length ; i++)
                {
                    // tutaj jesli nie ma 4 to wstawiasz do tablicy intów -1, a potem musisz to pominąć przy rysowaniu
                   if(wartosci.length < 4)
                   {
                       wartosciInt[3] = -1;
                   }
                   // tu musisz zrobić try catch'a dla tych co nie są liczbami, ale to już musisz ogarnąc sam
                    // Możesz sprawdzać stringa przed rzutowaniem go po kodzie ASCII
                    // czyli lecisz znak po znaku stringa i jeśli wartośc na któreś pozycji znajduje się poza zakresem 0 - 11 w ASCII to omijasz
                    wartosciInt[i] = Integer.valueOf(wartosci[i]);
                }

            for(int i = 0 ;i < wartosciInt.length; i++)
            {
                int pos = i+1;
                System.out.print(" | Wartosc " +pos+": " + wartosciInt[i]);
            }
            System.out.println();
        }

    }
}
