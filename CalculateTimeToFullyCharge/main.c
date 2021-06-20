#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <time.h>
#include <glob.h>
#include "charge.h"

int main() {

	do {

          FILE *fp_inEstimativa=NULL, *fp_inLock=NULL, *flagEstimativa;

        glob_t FlagI;
        glob_t DataI;

        do{
			printf("searching for flag file...\n");
                        sleep(3);
        		glob("lock_*.flag",0,NULL,&FlagI);
        		glob("lock_*.data",0,NULL,&DataI);
        		if(FlagI.gl_pathc>0){
        			fp_inLock=fopen(DataI.gl_pathv[0],"r");
        		} else {
        		    printf("Not detected\n");
        		}
        		
        }while(fp_inLock==NULL);

        remove(FlagI.gl_pathv[0]);

	printf("Processing files!\n");

        //Declarar variaveis com o tempo atual.
	    time_t tempo;
	    time(&tempo);
	    struct tm *local=localtime(&tempo);
	    int horas = local->tm_hour;
        int minutos = local->tm_min;
        int segundos = local->tm_sec;
        int dia = local->tm_mday;
        int mes = local->tm_mon + 1;
        int ano = local->tm_year + 1900;

        
		Battery* ptr_battery = (Battery *) calloc(1,sizeof(Battery));

        char *FileEstimativa, *FlagEstimativa;
		
		char s1[10], s2[10], s3[10];   
		int resto;     
     
		fscanf(fp_inLock,"%s %s %s", s1, s2, s3);
		fclose(fp_inLock);
		
		ptr_battery[0].battery = atoi(s1);
		ptr_battery[0].batteryCapacity = atoi(s2);
		ptr_battery[0].voltage = atoi(s3);
		
		ptr_battery[0].timeLeft = calculate_time_to_fully_charge(ptr_battery[0].battery,ptr_battery[0].batteryCapacity,ptr_battery[0].voltage); //calculating the time left
		resto = calculateResto(ptr_battery[0].battery,ptr_battery[0].batteryCapacity,ptr_battery[0].voltage);
		remove(DataI.gl_pathv[0]);

		FileEstimativa=(char *) malloc(50*sizeof(char));
		FlagEstimativa=(char *) malloc(50*sizeof(char));

		//Guarda no sprint buffer esta informacao.
		sprintf(FileEstimativa, "estimativa_%d_%d_%d_%d-%d_%d.data",ano,mes,dia,horas,minutos,segundos);
		sprintf(FlagEstimativa, "estimativa_%d_%d_%d_%d_%d_%d.flag",ano,mes,dia,horas,minutos,segundos);

        //Criacao dos ficheiros de estimativa (O ficheiro nao flag irá ter o tempo guardado)
		fp_inEstimativa=fopen(FileEstimativa,"a"); //appends to a file
		fprintf(fp_inEstimativa,"%d.%d",ptr_battery[0].timeLeft, resto);
		flagEstimativa=fopen(FlagEstimativa,"a");
		fclose(fp_inEstimativa);
		fclose(flagEstimativa);
		free(FileEstimativa);
		free(FlagEstimativa);

        free(ptr_battery);

	}while(0 == 0);
      

	return 0;
}
