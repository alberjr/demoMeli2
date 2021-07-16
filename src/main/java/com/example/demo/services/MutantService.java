package com.example.demo.services;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.constants.Parameter;
import com.example.demo.models.Dna;
import com.example.demo.models.output.StatMutant;
import com.example.demo.repositories.DnaRepository;
import com.example.demo.util.OperationUtil;
import com.example.demo.util.OperationUtil;

@Service
public class MutantService {
	

	@Autowired
	private DnaRepository dnaRepository;

	
	private boolean validateCount(int cont) {
		return cont<=Parameter.LIMIT_MIN_STRING;
	}

	private int horizontalSearch(String[] dna, int countSec) {

        //Verificacion Horizontal
        char charSec = 0;
        int indexSec = 0;
        int foundSec = countSec;
        int posCol = 0;
        int posFila=0;
        while (posFila < dna.length  && this.validateCount(foundSec)) {
            charSec = 0;
            indexSec = 0;
            posCol = 0;
            while (posCol < dna[0].length() && this.validateCount(foundSec) ) {
                if (charSec == OperationUtil.getValue(dna, posFila, posCol)) {
                    indexSec++;
                    if (indexSec == Parameter.NUM_CHAR_SEC) {
                        indexSec = 1;
                        charSec = 0;
                        foundSec++;
                    }
                } else {
                    charSec = OperationUtil.getValue(dna, posFila, posCol);
                    indexSec = 1;
                }
                posCol++;
            }
            posFila++;
        }
        System.out.println("Secuencias Horizontales:" + foundSec);
        return foundSec;
	}
	
	
	private int verticalSearch(String[] dna, int countSec) {

        //Verificacion Vertical
        char charSec = 0;
        int indexSec = 0;
        int foundSec = countSec;
        int posCol = 0;
        int posFila;
        while (posCol < dna[0].length() && this.validateCount(foundSec)) {
        	charSec = 0;
            indexSec = 0;
            posFila = 0;
            while (posFila < dna.length && this.validateCount(foundSec)) {
                if (charSec == OperationUtil.getValue(dna, posFila, posCol)) {
                    indexSec++;
                    if (indexSec ==  Parameter.NUM_CHAR_SEC) {
                        indexSec = 1;
                        charSec = 0;
                        foundSec++;
                    }
                } else {
                    charSec = OperationUtil.getValue(dna, posFila, posCol);
                    indexSec = 1;
                }
                posFila++;
            }
            posCol++;
        }
        System.out.println("Secuencias Verticales:" + foundSec);
        return foundSec;
	}
	
	
	private int diagonalDownPrinSearch(String[] dna, int countSec) {

        //Verificacion Triangulo principal inferior
        char charSec = 0;
        int indexSec = 0;
        int foundSec = countSec;
        int posCol = 0;
        int posFila=0;
        while (posFila < dna.length && this.validateCount(foundSec)) {
            charSec = 0;
            indexSec = 0;
            posCol = 0;
            while (posCol < dna[0].length() - posFila && this.validateCount(foundSec)) {
                if (charSec == OperationUtil.getValue(dna, posFila + posCol, posCol)) {
                    indexSec++;
                    if (indexSec ==  Parameter.NUM_CHAR_SEC) {
                        indexSec = 1;
                        charSec = 0;
                        foundSec++;
                    }
                } else {
                    charSec = OperationUtil.getValue(dna, posFila + posCol, posCol);
                    indexSec = 1;
                }
                posCol++;
            }
            posFila++;
        }
        System.out.println("Secuencias Diagonal hacia arriba:" + foundSec);
        return foundSec;
	}
	
	private int diagonalUpPrinSearch(String[] dna, int countSec) {

        //Verificacion Triangulo principal superior
        char charSec = 0;
        int indexSec = 0;
        int foundSec = countSec;
        int posCol = 0;
        int posFila=1;
        while (posFila < dna.length && this.validateCount(foundSec)) {
            charSec = 0;
            indexSec = 0;
            posCol = 0;
            while (posCol < dna[0].length() - posFila && this.validateCount(foundSec)) {
                if (charSec == OperationUtil.getValue(dna, posCol , posFila + posCol)) {
                    indexSec++;
                    if (indexSec ==  Parameter.NUM_CHAR_SEC) {
                        indexSec = 1;
                        charSec = 0;
                        foundSec++;
                    }
                } else {
                    charSec = OperationUtil.getValue(dna, posCol , posFila + posCol);
                    indexSec = 1;
                }
                posCol++;
            }

            posFila++;
        }
        System.out.println("Secuencias Diagonal hacia arriba:" + foundSec);
        return foundSec;
	}
	
	
	private int diagonalSecDownSearch(String[] dna, int countSec) {
		

        //Verificacion Triangulo secudario inferior
        char charSec = 0;
        int indexSec = 0;
        int foundSec = countSec;
        int posCol = 0;
        int posFila=0;

        while (posCol < dna[0].length() && this.validateCount(foundSec)) {
        	 charSec = 0;
             indexSec = 0;
             posFila=posCol;
            while (posFila < dna.length && this.validateCount(foundSec)) {
            	if (charSec == OperationUtil.getValue(dna, posFila, dna[0].length()-1+posCol-posFila)) {
                    indexSec++;
                    if (indexSec ==  Parameter.NUM_CHAR_SEC) {
                        indexSec = 1;
                        charSec = 0;
                        foundSec++;
                    }
                } else {
                    charSec = OperationUtil.getValue(dna, posFila, dna[0].length()-1+posCol-posFila);
                    indexSec = 1;
                }
            	posFila++;
            }
            posCol++;
        }
        System.out.println("Secuencias Diagonal hacia Abajo:" + foundSec);
        return foundSec;
	}
	
	
	private int diagonalSecUpSearch(String[] dna, int countSec) {
		

        //Verificacion Triangulo secudario inferior
        char charSec = 0;
        int indexSec = 0;
        int foundSec = countSec;
        int posCol = 1;
        int posFila=0;
        while (posCol < dna[0].length() && this.validateCount(foundSec)) {
       	 	charSec = 0;
            indexSec = 0;
            posFila=0;
            while (posFila < dna.length-posCol && this.validateCount(foundSec)) {
            	if (charSec == OperationUtil.getValue(dna, posFila, dna[0].length()-1-posCol-posFila)) {
		            indexSec++;
		            if (indexSec ==  Parameter.NUM_CHAR_SEC) {
		                indexSec = 1;
		                charSec = 0;
		                foundSec++;
		            }
		        } else {
		            charSec = OperationUtil.getValue(dna, posFila, dna[0].length()-1-posCol-posFila);
		            indexSec = 1;
		        }
            	posFila++;
            }
            posCol++;
        }
        
        System.out.println("Secuencias Diagonal hacia Abajo:" + foundSec);
        return foundSec;
	}
	
	private void saveMutant(Dna dna) {
		dna.setCreateDate(LocalDateTime.now());
		dnaRepository.save(dna);
	}
	
	public boolean isMutant(String [] dna){
		int countSec=0;
		boolean mutant=false;
		int n= dna.length;	
		int i=0;
		if(n>=Parameter.NUM_CHAR_SEC) {
			do {
				switch(i) {
					case 0:
						countSec=horizontalSearch(dna,countSec);
						break;
					case 1:
						countSec=verticalSearch(dna,countSec);
						break;
					case 2:
						countSec=diagonalDownPrinSearch(dna,countSec);
						break;
					case 3:
						countSec=diagonalUpPrinSearch(dna,countSec);
						break;
					case 4:
						countSec=diagonalSecDownSearch(dna,countSec);
						break;
					case 5:
						countSec=diagonalSecUpSearch(dna,countSec);
						break;
					default:
						break;
				}

				mutant=!this.validateCount(countSec);
				i++;
			}while(!mutant && i<6);
		}
		Dna dnaout= new Dna();
		dnaout.setMutant(mutant);
		dnaout.setDnaInfo(Arrays.toString(dna));
		this.saveMutant(dnaout);
		return mutant;
	}
	
	public StatMutant stats() {
		StatMutant stat= new StatMutant();
		stat.setCountHumanDna(dnaRepository.countByMutant(false));
		stat.setCountMutantDna(dnaRepository.countByMutant(true));
		System.out.println(stat.getCountMutantDna());
		System.out.println(stat.getCountHumanDna());
		if(stat.getCountHumanDna()>0) {
			stat.setRatio(( (double) stat.getCountMutantDna())/stat.getCountHumanDna());
		}		
		return stat;
		
	}
	
	
	
	

}
