package JogoFSM;

import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.Agent;

import javax.swing.JOptionPane;
        
public class ComportamentoFSM extends FSMBehaviour
{ 
   //Define as constantes dos estados
   private static final String ONE_STATE = "Ocioso";
   private static final String TWO_STATE = "Perseguindo_Inimigo";
   private static final String THREE_STATE = "Atacando_Inimigo";
   private static final String FOUR_STATE = "Descansando";
   private static final String ERROR_STATE= "EstadoErro";
   
   //Define as constantes das transições
   private final int UM = 1;
   private final int DOIS = 2;
   private final int TRES = 3;
   private final int QUATRO = 4;
   private final int CINCO = 5;
   private final int SEIS = 6;
   private final int ZERO = 0;
   
   private int transicao=0;
   private String entrada="";
 
   public ComportamentoFSM(Agent agente, String ent)
   {
      super(agente);
      entrada=ent;
   }

   @Override
   public void onStart()
   {
      //Relaciona cada constante com o respectivo objeto do comportamento 
      registerFirstState(new OciosoBehaviour(),ONE_STATE);
      registerState(new PerseguindoBehaviour(), TWO_STATE);
      registerState(new AtacandoBehaviour(), THREE_STATE);
      registerState(new DescansandoBehaviour(), FOUR_STATE);
      registerLastState(new ErrorBehaviour(),ERROR_STATE);
      
      //Define as transições entre os estados 
      registerTransition(ONE_STATE, TWO_STATE, UM);
      registerTransition(TWO_STATE, THREE_STATE,DOIS);
      registerTransition(THREE_STATE,FOUR_STATE,TRES);
      registerTransition(FOUR_STATE,ONE_STATE,QUATRO);
      registerTransition(TWO_STATE, ONE_STATE,CINCO);
      registerTransition(TWO_STATE, FOUR_STATE,TRES);
      registerTransition(THREE_STATE,TWO_STATE,SEIS);
      
      
      registerDefaultTransition(ONE_STATE, ERROR_STATE);
      registerDefaultTransition(TWO_STATE, ERROR_STATE);
      registerDefaultTransition(THREE_STATE, ERROR_STATE);
      registerDefaultTransition(FOUR_STATE, ERROR_STATE);      
      
   }
 
   /*
    * Método chamado após a execução de cada sub-comportamento (cada filho), 
    * com o objetivo de verificar se o CompositeBehaviour deve terminar, ou não
    */
   @Override
   protected boolean checkTermination(boolean currentDone, int currentResult)
   {
      System.out.println("   ** Terminado estado número: " + currentName);
      System.out.println("-----------------------------");
      //currentDone: uma variável boleana indicando se o recém executado 
      // subcomportamento (filho) está completado, ou não.
      //currentResult: o valor final (como retornado pelo método onEnd()) do 
      //subcomportamento recém executado, no caso do subcomportamento ter sido 
      // completado
      
      return super.checkTermination(currentDone,currentResult);
   }
 
   public int getEntrada()
   {  
       /*
      int tipoEvento = ZERO;
      if (entrada.length()<1) 
         return tipoEvento;
      else 
         tipoEvento=Integer.parseInt(entrada.substring(0,1));
      entrada=entrada.substring(1,entrada.length());
      
      System.out.println("tipoEvento = " + tipoEvento);
      System.out.println("entrada = " + entrada);
         
      return tipoEvento;
      */
      
      int tipoEvento;
      String mensagem = "Você está no estado " + currentName;
      mensagem = mensagem + "\nDigite o evento a ser executado \n1: Inimigo a vista;\n2: Inimigo ao alcance do ataque; \n3: Saúde baixa;\n4: Saúde recuperada; \n5: Inimigo fugiu; \n6: Inimigo fora de alcance  \n0: Finalizar";
      tipoEvento = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
      return tipoEvento;      
   }
   
   //CHAMANDO AS CLASSES PRIVADAS
   private class OciosoBehaviour extends OneShotBehaviour 
   {
      @Override
      public void action()
      {
          System.out.println("() Passei pelo primeiro estado Ocioso");
      }
      
      @Override
      public int onEnd() 
      { 
          System.out.println("Passei pelo método onEnd() do primeiro estado Ocioso");
          return getEntrada();
      }
   }
 
   private class PerseguindoBehaviour extends OneShotBehaviour 
   {
      @Override
      public void action()
      {
          System.out.println("() Passei pelo segundo estado Perseguindo um Inimigo");  
      }
      
      @Override
      public int onEnd() 
      {
          System.out.println("Passei pelo método onEnd() do segundo estado Perseguindo um Inimigo");          
          return getEntrada();
      }
   }
 
   private class AtacandoBehaviour extends OneShotBehaviour 
   {
      @Override
      public void action()
      {
          System.out.println("() Passei pelo terceiro estado Atacando um inimigo"); 
      }
      @Override
      public int onEnd() 
      { 
          System.out.println("Passei pelo método onEnd() do terceiro estado Atacando um inimigo");          
          return getEntrada();
      }
   }
   
   
      private class DescansandoBehaviour extends OneShotBehaviour 
   {
      @Override
      public void action()
      {
          System.out.println("() Passei pelo quarto estado Descansando"); 
      }
      @Override
      public int onEnd() 
      { 
          System.out.println("Passei pelo método onEnd() do quarto estado Descansando");          
          return getEntrada();
      }
   }
 
   private class ErrorBehaviour extends OneShotBehaviour 
   {
      @Override
      public void action()
      {        
        System.out.println("() Passei pelo estado default");
      }
   }
}