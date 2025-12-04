import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class BotWhatsApp {

    public static void main(String[] args) {
        // Usando try-with-resources para garantir o fechamento do scanner
        try (Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("=== Bot de Automação de Mensagens ===");
            
            // 1. Configuração da Mensagem
            System.out.print("Digite a mensagem a ser enviada: ");
            String mensagem = scanner.nextLine();

            System.out.print("Quantas vezes deseja enviar? ");
            int quantidade = scanner.nextInt();

            System.out.print("Intervalo entre mensagens (em milissegundos, ex: 1000): ");
            int intervalo = scanner.nextInt();

            // 2. Copiar para o Clipboard (Ctrl+C virtual)
            copiarParaAreaDeTransferencia(mensagem);

            System.out.println("\n[ATENÇÃO] Clique na janela do WhatsApp AGORA!");
            System.out.println("O envio começará em 5 segundos...");
            Thread.sleep(5000);

            // 3. Inicializar o Robô
            Robot robot = new Robot();

            // 4. Loop de envio
            for (int i = 1; i <= quantidade; i++) {
                enviarMensagem(robot);
                System.out.println("Mensagem " + i + " de " + quantidade + " enviada.");
                Thread.sleep(intervalo);
            }

            System.out.println("Concluído!");

        } catch (AWTException | InterruptedException e) {
            System.err.println("Ocorreu um erro na execução do Bot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Coloca a string na área de transferência do sistema (Ctrl+C).
     */
    private static void copiarParaAreaDeTransferencia(String texto) {
        StringSelection stringSelection = new StringSelection(texto);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * Simula o Ctrl+V e Enter.
     */
    private static void enviarMensagem(Robot robot) {
        // Pressiona Ctrl+V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Pequeno delay para garantir que colou antes de dar Enter
        robot.delay(100); 

        // Pressiona Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}