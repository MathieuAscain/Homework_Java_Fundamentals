package enonce;

public class Catalogue {
    private static String[] tabNoms = { "Stylo bleu", "Stylo rouge", "Cahier petit format", "Cahier grand format" };
    private static double[] tabPrix = { 1.2, 1.25, 2.00, 3.00 };

    public static void main(String[] args) {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("Catalogue initial :");
        afficher();
        ajouter("Classeur A4", 1.55);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("Catalogue avec classeur A4 en plus :");
        afficher();
        ajouter("Ramette A4", 2.55);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("Catalogue avec classeur A4 et ramette A4 en plus :");
        afficher();

        int styloBleu = chercher("Stylo bleu");
        if (styloBleu != -1) {
            System.out.println("Produit " + styloBleu + " : " + getNom(styloBleu) + " = " + getPrix(styloBleu) + "€");
        } else {
            System.out.println("Pas de stylo bleu");
        }
        int classeur = chercher("Classeur A4");
        if (classeur != -1) {
            System.out.println("Produit " + classeur + " : " + getNom(classeur) + " = " + getPrix(classeur) + "€");
        } else {
            System.out.println("Pas de classeur A4");
        }
        int trousse = chercher("Trousse");
        if (trousse != -1) {
            System.out.println("Produit " + trousse + " : " + getNom(trousse) + " = " + getPrix(trousse) + "€");
        } else {
            System.out.println("Pas de trousse");
        }
    }

    /**
     * retourne le nom du produit de numéro numProduit.
     *
     * @param numProduit
     *            le numéro du produit
     * @return le nom du produit
     */
    public static String getNom(int numProduit) {
        return tabNoms[numProduit];
    }

    /**
     * retourne le prix du produit de numéro numProduit.
     *
     * @param numProduit
     *            le numéro du produit
     * @return le prix du produit
     */
    public static double getPrix(int numProduit) {
        return tabPrix[numProduit];
    }

    /**
     * affiche le catalogue à l'écran.
     */
    public static void afficher() {
        System.out.println("--------------------------");
        for(int i = 0; i < tabPrix.length; i++) {
            System.out.println(getNom(i) + " = " + getPrix(i) + "€");
        }
        System.out.println("--------------------------");
    }

    /**
     * La méthode <code>ajouter(String nom, double prix)</code> doit permettre
     * d'ajouter un nouveau produit à la structure de données. Il faudra créer
     * deux tableaux, un pour les noms et un pour les prix avec une case de plus
     * que tabNoms et tabPrix. Puis, recopier le contenu de tabNoms et tabPrix
     * dans les nouveaux tableaux. Ensuite, il faudra ajouter le nom et le prix
     * du produit dans les dernières cases des deux nouveaux tableaux. Enfin, il
     * faudra que tabNoms et tabPrix désignent ces nouveaux tableaux.
     *
     * @param nom
     *            le nom du nouveau produit
     * @param prix
     *            le prix du nouveau produit
     */
    public static void ajouter(String nom, double prix) {
        String[] nouveauNom = new String[tabNoms.length + 1];
        double [] nouveauPrix = new double[tabPrix.length + 1];
        for(int i = 0; i < tabNoms.length; i++) {
            nouveauNom[i] = tabNoms[i];
            nouveauPrix[i] = tabPrix[i];
        }
        nouveauNom[nouveauNom.length - 1] = nom;
        nouveauPrix[nouveauPrix.length - 1] =  prix;
        tabNoms = nouveauNom;
        tabPrix = nouveauPrix;
    }

    /**
     * cherche dans le catalogue le produit dont le nom est passé en paramètre
     * et retourne son numéro. Si le produit n'est pas trouvé, la méthode
     * retournera -1.
     *
     * Pour savoir si le nom du produit numéro i est égal au nom cherché,
     * n'utilisez pas == mais, plutôt, l'expression nom.equals(tabNoms[i]) qui
     * retourne true si les deux textes sont identiques ou false dans le cas
     * contraire.
     *
     * @param nom
     *            le nom du produit cherché
     * @return le numéro du produit dans le catalogue si le produit est présent
     *         sinon -1.
     */
    public static int chercher(String nom) {
        int indice = 0;
        while(indice < tabNoms.length && !nom.equals(getNom(indice))){
            indice++;
        }
        if(indice == tabNoms.length) {
            return -1;
        } else {
            return indice;
        }
    }
}
