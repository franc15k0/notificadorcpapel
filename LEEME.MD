      Instrucciones
Comando para ejecutar job NOtificador Cero Papel:
java -jar notificadorcpapel.jar --spring.config.location=application.properties  > notificador.log 2>&1

Lista Cron Expresiones Ejemplos:

At 12:00 pm (noon) every day during the year 2017:
0 0 12 * * ? 2017

Every 5 minutes starting at 1 pm and ending on 1:55 pm and then starting at 6 pm and ending at 6:55 pm, every day:
0 0/5 13,18 * * ?
Every minute starting at 1 pm and ending on 1:05 pm, every day:
0 0-5 13 * * ?

At 1:15 pm and 1:45 pm every Tuesday in the month of June:
0 15,45 13 ? 6 Tue

At 9:30 am every Monday, Tuesday, Wednesday, Thursday, and Friday:
0 30 9 ? * MON-FRI

At 9:30 am on 15th day of every month:
0 30 9 15 * ?

At 6 pm on the last day of every month:
0 0 18 L * ?

At 6 pm on the 3rd to last day of every month:
0 0 18 L-3 * ?

At 10:30 am on the last Thursday of every month:
0 30 10 ? * 5L

At 6 pm on the last Friday of every month during the years 2015, 2016 and 2017:
0 0 18 ? * 6L 2015-2017

At 10 am on the third Monday of every month:
0 0 10 ? * 2#3


At 12 am midnight on every day for five days starting on the 10th day of the month:
0 0 0 10/5 * ?
