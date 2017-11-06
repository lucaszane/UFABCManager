package JogoFSM;

import jade.core.Agent;

public class PersonagemFSM extends Agent 
{
   private String entrada="";
   
   @Override
   public void setup() 
   {
      entrada="231";
      
      ComportamentoFSM b = new ComportamentoFSM(this, entrada);
      addBehaviour(b);
   }
}