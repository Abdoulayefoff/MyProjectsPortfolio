#!/bin/bash

while true; do
    echo "Choisissez une option svp :"
    echo "1. Tapez 1 pour compiler toutes les classes"
    echo "2. Taper 2 pour lancer la demo des contraintes (DemoOfConstraint)"
    echo "3. Taper 3 pour lancer la demo de la planification (DemoPlanification)"
    echo "4. Tapez 4 pour lancer la demo du CSP (DemoCSP)"
    echo "5. Tapez 5 pour lancer la demo de l'extraction (DemoExtraction)"
    echo "6. Quitter"
    read -p "Entrez votre choix (1-6) : " choix

    case $choix in
        1)
            echo "Compilation de toutes les classes"
            javac -d build --class-path "lib/*" src/**/*.java
            echo "Compilation terminée."
            ;;
        2)
            echo "Demo des Contraintes"
            java -cp build:lib/* blocksworld.DemoOfContraintes
            ;;
        3)
            echo "Demo Plannification"
            java -cp build:lib/* blocksworld.DemoPlanification 
            ;;
        4)
            echo "Demo CSP"
            java -cp build:lib/* blocksworld.DemoCSP
            ;;
        5)
            echo "Demo Extraction de connaissances"
            java -cp build:lib/* blocksworld.DemoExtraction
            ;;
        6)
            echo "Au revoir !"
            exit 0
            ;;
        *)
            echo "Choix invalide. Veuillez réessayer"
            ;;
    esac

    echo ""
done

