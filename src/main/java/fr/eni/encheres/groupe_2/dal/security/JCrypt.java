package fr.eni.encheres.groupe_2.dal.security;


public class JCrypt {

    private final String SECRET_KEY="encrypt";

    public JCrypt() {

    }

    private char[][] tableauEncodage(){
        char [][] alphabet =new char[26][26];
        for (int i= 0; i < alphabet.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                int lettre = ((i +j) % 26) + 65;
                alphabet[i][j] = (char) lettre;
            }
        }
        return alphabet;
    };

    /**
     * Crypt le mot de passe sur la base de l'exercice en java procedural
     * @param textToEncrypt String mot de pass
     * @return Stringbuilder , mot de passe crypter
     */
    public StringBuilder encrypt(String textToEncrypt,int keySk){
        System.out.println(textToEncrypt);
        StringBuilder cryptedText= new StringBuilder();
        char[][] cryptArray = this.tableauEncodage();
        String secretKey = this.SECRET_KEY.toUpperCase();
        System.out.println(secretKey);
        int secretIndex=0;
        for (int i = 0; i < textToEncrypt.length() ; i++) {
            int indexText = textToEncrypt.charAt(i)-65;
            int indexKey = secretKey.charAt(secretIndex)-65;
            cryptedText.append(cryptArray[indexText][indexKey]);
            secretIndex++;
            if(secretIndex == secretKey.length()){
                secretIndex=0;
            }
        };
       return cryptedText;
    };
    /**
     * deCrypt le mot de passe sur la base de l'exercice en java procedural
     * @param textToDecrypt String mot de pass
     * @return Stringbuilder , mot de passe decrypter
     */
    public StringBuilder decrypt(String textToDecrypt,int keySk){
        StringBuilder decryptedText = new StringBuilder();
        char[][] cryptArray = this.tableauEncodage();
        String secretKey = this.SECRET_KEY.toUpperCase();
        int secretIndex;
        int sKey=0;
        for (int i = 0; i <textToDecrypt.length() ; i++) {
            int indexText=0;
            secretIndex = secretKey.charAt(sKey)-65;
            while (cryptArray[indexText][secretIndex] != textToDecrypt.charAt(i)){
                indexText++;
            };
            sKey++;
            decryptedText.append((char)(65+indexText));
            if(sKey == secretKey.length()){
                sKey=0;
            }
        }
       return decryptedText;
    };
}
