import edu.duke.*;

public class Tester {
    
    public void CaesarCipherTester () {
        int theKey = 5;
        CaesarCipher cc = new CaesarCipher(theKey);
        FileResource fr = new FileResource("data/titus-small.txt");
        for (String line : fr.lines()){
            String encrypt = cc.encrypt(line);
            System.out.println(encrypt);
            
            String decrypt = cc.decrypt(encrypt);
            System.out.println(decrypt);
            
            System.out.println("");
        }        
    }
    
    public void VignereCipherTester () {
        int[] key = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource("data/titus-small.txt");
        for (String line : fr.lines()){
            String encrypt = vc.encrypt(line);
            System.out.println(encrypt);
            
            String decrypt = vc.decrypt(encrypt);
            System.out.println(decrypt);
            
            System.out.println("");
        }
    }
        
}
