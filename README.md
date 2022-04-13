# Proyecto 1 Wizard

## Integrantes

Lopez Villalba Cielo.
Rodriguez Belmonte Lázaro Eduardo.

## Compilar y Correr

Compilar -> javac -d . src/Wizard/*.java

Correr   -> java edd.src.Wizard.WizardUI

## Convenciones y observaciones

Los indices para jugar una carta se comienzan a contar desde el 0.

## Estructura del proyecto

El proyecto fue diseñado tratando de separar en las actividades de diferentes
objetos para una implementacion más sencilla, usando la filosofia orientada a objetos.
Por lo anterior es que se tienen diversas clases de practicamente cada ente que existe
segun las reglas del juego Wizard.

Un inconveniente que se tuvo a la hora de programar fue el que no sabia como conservar 
los indices del ganador de una ronda o truco haciendo uso de listas, sin embargo
con el pasar de los dias me di cuenta que las listas no eran la opción optima si es que
tenia la misma cantidad de jugadores para todo un juego y queria acceder a estos en tiempo constante
por eso es que al final opté por un array de jugadores lo cual me permitió implementar 
la ronda de forma mas sencilla.