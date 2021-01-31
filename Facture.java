package enonce;

public class Facture {
    /* <numFacture><numProduit>*/
    private static int[][] tabProduits = new int[0][0];
    // <numFacture><quantiteProduit>
    private static int[][] tabQtes = new int[0][0];

    private static int nbFactures = 0;

    public static void main(String[] args) {
        int numFacture = nouvelleFacture();
        ajouterProduit(numFacture, 0, 10);
        ajouterProduit(numFacture, 3, 2);
        afficherFacture(numFacture);

        numFacture = nouvelleFacture();
        ajouterProduit(numFacture, 1, 1);
        ajouterProduit(numFacture, 0, 5);
        ajouterProduit(numFacture, 2, 5);
        afficherFacture(numFacture);

        afficher();
    }

    public static int nouvelleFacture() {
        nbFactures++;
        //Redimensionner les tableaux
        int[][] nouveauTabProduits = new int[nbFactures][1];
        int[][] newTabQtes = new int[nbFactures][1];

        //Copier les anciennes données
        for(int i = 1; i < nbFactures; i++) {
            nouveauTabProduits [i - 1] = tabProduits[i - 1];
            newTabQtes[i - 1] = tabQtes[i - 1];
        }
        tabProduits = nouveauTabProduits ;
        tabQtes = newTabQtes;

        //Initialiser la deuxième dimension
        tabProduits[nbFactures - 1] = new int[0];
        tabQtes[nbFactures - 1] = new int[0];

        // Retourne l'index du tableau qui devra être utilisé pour cette facture
        return nbFactures - 1;
    }

    public static void ajouterProduit(int numFacture,
                                       int numProduit,
                                       int quantite) {

        int nouveauNbElements = tabProduits[numFacture].length + 1;

        //Redimensionner tableau
        int[] nouveauProduits = new int[nouveauNbElements];
        int[] nouvellesQts = new int[nouveauNbElements];

        //Copier ancienne valeurs
        for (int i = 1; i < nouveauNbElements; i++) {
            nouveauProduits[i - 1] = tabProduits[numFacture][i - 1];
            nouvellesQts[i - 1] = tabQtes[numFacture][i - 1];
        }

        //Affecter nouvelle valeur
        nouveauProduits[nouveauNbElements - 1] = numProduit;
        nouvellesQts[nouveauNbElements - 1] = quantite;

        tabProduits[numFacture] = nouveauProduits;
        tabQtes[numFacture] = nouvellesQts;
    }

    /**
     * Affiche une facture avec son numéro, la liste des produits (nom du
     * produit, quantité, prix unitaire et prix total de la ligne), et le total
     * de la facture.
     *
     * @param numFacture
     *            numéro de la facture
     */
    public static void afficherFacture(int numFacture) {
        if(numFacture < 0 || numFacture >= nbFactures) {
            System.out.println("Facture inexistante");
        } else {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("Facture n° " + numFacture);
            System.out.println("--------------------------");
            int index = 0;
            double sommeTotale = 0;
            for (int numProduit : tabProduits[numFacture]) {
                System.out.println(Catalogue.getNom(numProduit) + " (" + Catalogue.getPrix(numProduit) + "€ par pièce) x " + tabQtes[numFacture][index] + " pièces = " + Catalogue.getPrix(numProduit) * tabQtes[numFacture][index] + "€");
                sommeTotale += Catalogue.getPrix(numProduit) * tabQtes[numFacture][index];
                index++;
            }
            System.out.println("--------------------------");
            System.out.println("Montant total : " + sommeTotale + "€");
        }
    }

    /**
     * affiche toutes les factures.
     */
    public static void afficher() {
        System.out.println();
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
        for (int i = 0; i < nbFactures; i++) {
            afficherFacture(i);
        }
        System.out.println();
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
    }
}