package jogodavelha;

import javax.swing.JOptionPane;

public class Tabuleiro {
    char casa[][] = new char[3][3];
    private String tab;
    private int moves;
    
    Tabuleiro() {//construtor
        for(int i=0; i<3; i++) {//seta a matriz toda para o caracter ' '
            for(int j=0; j<3;j++) {
                this.casa[i][j] = ' ';
            }
        }
        
        this.tab = "";//tabuleiro zerado
        this.moves = 0;//nenhum movimento feito
    }
    
    int SetValue(int l, int c, int jogador) {
        if(jogador == 1) {//se for o jogador 1
            if(this.casa[l][c] == ' ') {//se a casa estiver zerada
                this.casa[l][c] = '❌';
                moves++;
            }
            else {//casa ocupada
                return 1;
            }
        }
        else {//se for o jogador 2
            if(this.casa[l][c] == ' ') {//se a casa estiver zerada
                this.casa[l][c] = '⭕';
                moves++;
            }
            else {//casa ocupada
                return 1;
            }
        }
        
        return 0;//movimento feito, casa preenchida
    }
    
    String RetornaTabuleiro() {//retorna uma String com a situação do tabuleiro
        return "TABULEIRO:\n\n"
                + this.casa[0][0] + "   |   " + this.casa[0][1] + "   |   " + this.casa[0][2]
                + "\n--------------\n"
                + this.casa[1][0] + "   |   " + this.casa[1][1] + "   |   " + this.casa[1][2]
                + "\n--------------\n"
                + this.casa[2][0] + "   |   " + this.casa[2][1] + "   |   " + this.casa[2][2];
    }
    
    int Vitoria() {//retorna 1 se o jogador 1 ganhou, 2 para o jogador 2 e 0 para nenhum
        int winner = 0;
        
        for(int i=0; i<3; i++) {//vasculha 3 vezes
            if(this.casa[0][i] == this.casa[1][i] && this.casa[1][i] == this.casa[2][i] && winner == 0) {//vertical
                if(this.casa[0][i] == '❌')
                    winner = 1;
                else if (this.casa[0][i] == '⭕')
                    winner = 2;
                else
                    winner = 0;
                
            }
            
            if(this.casa[i][0] == this.casa[i][1] && this.casa[i][1] == this.casa[i][2] && winner == 0) {//horizontal
                if(this.casa[i][0] == '❌')
                    winner = 1;
                else if(this.casa[i][0] == '⭕')
                    winner = 2;
                else
                    winner = 0;
            }
        }
        
        if(this.casa[0][0] == this.casa[1][1] && this.casa[1][1] == this.casa[2][2]) {//diagonal principal
            if(this.casa[1][1] == '❌')
                winner = 1;
            else if(this.casa[1][1] == '⭕')
                winner = 2;
            else
                winner = 0;
        }
        
        if(this.casa[0][2] == this.casa[1][1] && this.casa[1][1] == this.casa[2][0]) {//diagonal secundária
            if(this.casa[1][1] == '❌')
                winner = 1;
            else if(this.casa[1][1] == '⭕')
                winner = 2;
            else
                winner = 0;
        }
        
        return winner;
    }
    
    void PrintaVitoria(int jogador) {//printa tabuleiro com a mensagem do ganhador
        String message = "GANHADOR: Jogador "
                + jogador + "\n\n" + this.RetornaTabuleiro();
        
        JOptionPane.showMessageDialog(null, message);
    }
    
    void PrintaTabuleiroSeta(int jogador) {//printa o tabuleiro e seta os valores. Caso haja ganhador, chama a mensagem
        int linha, coluna, vitoria;
        String message;
        
        //printa tabuleiro
        this.tab = this.RetornaTabuleiro()
                + "\n\nJOGADOR " + jogador + ", escolha a linha:";
        
        //jogador entra resposta
        linha = Integer.parseInt(JOptionPane.showInputDialog(tab));
        linha--;//para ficar intuitivo
        
        //printa novamente
        this.tab = this.RetornaTabuleiro()
                + "\n\nJOGADOR " + jogador + ", escolha a coluna:";
        
        //jogador entra resposta
        coluna = Integer.parseInt(JOptionPane.showInputDialog(tab));
        coluna--;//para ficar intuitivo
        
        if(this.SetValue(linha, coluna, jogador) == 0) {//colocou o valor na casa
            vitoria = this.Vitoria();
            
            if(vitoria == 0) {//não houve vitória
                if(moves >= 9) {//todas as casas estão preenchidas
                    message = "DEU VELHA:\n\n"
                            + this.RetornaTabuleiro();
                    
                    JOptionPane.showMessageDialog(null, message);
                }
                else {//ainda há casas vazias
                    if(jogador == 1) {//se estivermos no jogador 1
                        this.PrintaTabuleiroSeta(2);//chama o jogador 2
                    }
                    else {//se estivermos no jogador 2
                        this.PrintaTabuleiroSeta(1);//hama o jogador 1
                    }
                }
            }
            else if(vitoria == 1) {//jogador 1 ganhou
                this.PrintaVitoria(1);
            }
            else {//jogador 2 ganhou
                this.PrintaVitoria(2);
            }
        }
        else {//a casa está ocupada
            JOptionPane.showMessageDialog(null, "Esta casa já está ocupada.");
            this.PrintaTabuleiroSeta(jogador);
        }
    }
}
