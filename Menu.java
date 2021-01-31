package enonce;

import java.util.Scanner;

public class Menu {
    // Contrôle de la saisie pour le switch
    public static int checkSwitch(){
        Scanner myObj = new Scanner(System.in);
        int nombre = 0;
        boolean correct = false;
        do {
            try {
                String input = myObj.nextLine();
                nombre = Integer.parseInt(input);
                if(nombre < 0 || nombre > 7) {
                    throw new InvalidIntException("Erreur saisie");
                }
                correct = true;
            } catch(NumberFormatException | InvalidIntException e) {
                System.out.println("SVP entrez un nombre entre 1 et 7");
                System.out.print("Saisissez votre choix ................................ ");
            }
        } while(!correct );
        return nombre;
    }

    // Contrôle de la saisie pour une variable de type int
    public static int checkInt() {
        Scanner myObj = new Scanner(System.in);
        int nombre = 0;
        boolean correct = false;
        do {
            try {
                String input = myObj.nextLine();
                nombre = Integer.parseInt(input);
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("SVP entrez un nombre entier");
                System.out.print("Saisissez votre choix ................................ ");
            }
        } while (!correct);
        return nombre;
    }

        // Contrôle de la saisie pour une variable de type double
    public static double checkDouble() {
        Scanner myObj = new Scanner(System.in);
        double nombre = 0;
        boolean correct = false;
        do {
            try {
                String input = myObj.nextLine();
                nombre = Double.parseDouble(input);
                correct = true;
            } catch(NumberFormatException e) {
                System.out.println("SVP entrez un nombre (virgules permises)");
                System.out.print("Saisissez votre choix ................................ ");
            }
        } while(!correct);
        return nombre;
    }

    // Etape du switch : Tapez 1
    public static void afficherProduit() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Entrez le nom du produit ............................. ");
        String input = myObj.nextLine();
        int indice = Catalogue.chercher(input);
        if(indice == -1) {
            System.out.println("Le produit demandé n'est pas dans le catalogue");
        } else {
            System.out.println("Le produit demandé : " + Catalogue.getNom(indice) + " est bien dans le catalogue");
        }
        System.out.println();
    }

    // Etape du switch : Tapez 2
    // la méthode
    public static void ajouterProduit() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Entrez le nom du produit à ajouter ................... ");
        String nomProduit = myObj.nextLine();
        if(Catalogue.chercher(nomProduit) == - 1) {
            System.out.print("Entrez le prix du produit à ajouter .................. ");
            double prix = checkDouble();
            Catalogue.ajouter(nomProduit, prix);
            System.out.println("Produit ajouté au catalogue");
            System.out.println();
        } else {
            System.out.println("Le produit est déjà au catalogue");
        }
    }

    // Etape du switch : Tapez 3
    public static void afficherCatalogueComplet() {
        Catalogue.afficher();
        System.out.println();
    }

    // Etape du switch : Tapez 4
    public static void afficherFactureParNumero() {
        int numeroFacture = checkInt();
        Facture.afficherFacture(numeroFacture);
    }

    // Etape du switch : Tapez 5
    public static void ajoutFacture() {
        Scanner myObj = new Scanner(System.in);
        int numeroFacture = Facture.nouvelleFacture();
        System.out.println("Création de la facture " + numeroFacture);
        // création d'un boucle pour ajouter autant de produits souhaités à la même facture
        boolean continuer = true;
        do {
            System.out.print("Entrez le nom du produit à ajouter ................... ");
            String nomProduit = myObj.nextLine();
            // vérification que le produit soit dans le catalogue
            int indice = Catalogue.chercher(nomProduit);
            if(indice == -1) {
                System.out.println("Le produit demandé n'est pas dans le catalogue");
            } else {
                //System.out.println("Vous avez choisi le produit " + Catalogue.getNom(indice));
                System.out.print("Quelle quantité acheter ? ............................ ");
                int quantite = checkInt();
                Facture.ajouterProduit(numeroFacture,indice,quantite);
            }
            System.out.print("Souhaitez-vous ajouter un autre produit ? Y/N ........ ");
            String choix = myObj.nextLine();
            choix = choix.toUpperCase();
            if(!choix.equals("Y")) continuer = false;
        }while(continuer);
        System.out.println("Facture " + numeroFacture + " ajoutée. FIN");
    }

    // Etape du switch : Tapez 6
    public static void afficherFacturier() {
        Facture.afficher();
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Que voulez-vous faire ?");
        System.out.println("Afficher un produit à partir de son nom ? ............ Tapez 1");
        System.out.println("Ajouter un produit ? ................................. Tapez 2");
        System.out.println("Afficher le catalogue complet ? ...................... Tapez 3");
        System.out.println("Afficher une facture par son numéro ? ................ Tapez 4");
        System.out.println("Ajout d’une facture ? ................................ Tapez 5");
        System.out.println("Afficher le facturier ? .............................. Tapez 6");
        System.out.println("Sortie du programme ? ................................ Tapez 7");
        System.out.println();

        boolean continuer = true;
        do {
            System.out.print("Saisissez votre choix ................................ ");
            int choix = checkSwitch();
            switch (choix) {
                case 1 -> afficherProduit();
                case 2 -> ajouterProduit();
                case 3 -> afficherCatalogueComplet();
                case 4 -> afficherFactureParNumero();
                case 5 -> ajoutFacture();
                case 6 -> afficherFacturier();
                case 7 -> {
                    System.out.println("Sortie du programme, au revoir :)");
                    continuer = false;
                }
                default -> {
                    System.out.println("Erreur inattendue");
                    continuer = false;
                }
            }
        }while(continuer);
    }
}
