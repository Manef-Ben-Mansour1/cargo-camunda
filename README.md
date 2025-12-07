# HelloworldCamunda

Application de démonstration Camunda BPM avec un processus "Helloworld" simple.

## Description

Ce projet est une application web Camunda BPM qui déploie un processus métier simple. L'application utilise l'annotation `@ProcessApplication` pour être automatiquement découverte et déployée par le serveur Camunda.

## Prérequis

- Java 8 ou supérieur
- Maven 3.x
- Camunda BPM 7.18.0 (ou version compatible)
- IntelliJ IDEA (recommandé)

## Structure du Projet

```
HelloworldCamunda/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── tn/insat/urb/tp3/
│   │   │       └── HelloworldApplication.java    # Classe ProcessApplication
│   │   ├── resources/
│   │   │   ├── helloworld.bpmn                   # Définition du processus BPMN
│   │   │   └── META-INF/
│   │   │       ├── processes.xml                 # Configuration du déploiement
│   │   │       └── MANIFEST.MF                   # Manifest pour le WAR
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           └── web.xml                      # Configuration web
│   └── test/
└── pom.xml                                        # Configuration Maven
```

## Composants Principaux

### HelloworldApplication.java

Classe principale qui étend `ServletProcessApplication` et est annotée avec `@ProcessApplication("Helloworld App")`. Cette annotation permet à Camunda de découvrir et déployer automatiquement l'application.

### processes.xml

Fichier de configuration qui définit :
- Le nom de l'archive de processus : `helloworld`
- Le moteur de processus : `default`
- Les ressources BPMN à déployer
- Les propriétés de déploiement

### helloworld.bpmn

Définition du processus métier en BPMN 2.0.

## Construction du Projet

### Avec Maven

```bash
mvn clean package
```

Le fichier WAR sera généré dans `target/HelloworldCamunda-1.war`.

### Avec IntelliJ IDEA

1. Ouvrir le projet dans IntelliJ IDEA
2. Menu `Build` > `Build Project` (ou `Ctrl+F9`)
3. Le WAR sera généré dans le répertoire `target/`

## Déploiement

### Option 1 : Déploiement Automatique avec IntelliJ Artifact

1. **Configurer l'Artifact dans IntelliJ** :
   - Menu `File` > `Project Structure...` (ou `Ctrl+Alt+Shift+S`)
   - Cliquer sur `Artifacts`
   - Si l'artifact n'existe pas, cliquer sur `+` > `Web Application: Archive` > `For 'HelloworldCamunda:war'`
   - Sélectionner l'artifact `HelloworldCamunda`

2. **Définir le répertoire de sortie** :
   - Dans `Output directory`, cliquer sur `...`
   - Naviguer vers le répertoire webapps de votre installation Camunda :
     ```
     $CAMUNDA_HOME/server/apache-tomcat-<version>/webapps
     ```
     Exemple : `C:\camunda-bpm-tomcat-7.18.0\server\apache-tomcat-9.0.xx\webapps`

3. **Créer le Manifest** (si nécessaire) :
   - Cliquer sur `HelloworldCamunda-1.war` dans la structure
   - En bas de la fenêtre, cliquer sur `Create Manifest`
   - Sélectionner le répertoire : `src/main/resources`
   - Le fichier `MANIFEST.MF` sera créé dans `src/main/resources/META-INF/`

4. **Build l'Artifact** :
   - Menu `Build` > `Build Artifacts...`
   - Sélectionner `HelloworldCamunda:war` > `Build`
   - Ou cliquer sur `All Artifacts` > `Build`

Le fichier `HelloworldCamunda-1.war` sera créé dans le répertoire webapps de Camunda et déployé automatiquement au démarrage du serveur.

### Option 2 : Déploiement Manuel

1. Construire le WAR avec Maven :
   ```bash
   mvn clean package
   ```

2. Copier le WAR vers le répertoire webapps de Camunda :
   ```bash
   # Windows
   copy target\HelloworldCamunda-1.war <CAMUNDA_HOME>\server\apache-tomcat-<version>\webapps\
   
   # Linux/Mac
   cp target/HelloworldCamunda-1.war $CAMUNDA_HOME/server/apache-tomcat-<version>/webapps/
   ```

3. Démarrer le serveur Camunda (si ce n'est pas déjà fait)

## Vérification du Déploiement

Une fois le serveur Camunda démarré, vous pouvez vérifier le déploiement :

1. **Cockpit Camunda** : Accéder à `http://localhost:8080/camunda/app/cockpit/`
2. **Vérifier les processus déployés** : Dans Cockpit, aller dans "Process Definitions" pour voir le processus "helloworld"
3. **Vérifier l'application** : L'application devrait être visible dans la liste des applications déployées

## Structure du WAR Généré

Le fichier WAR contiendra :
```
HelloworldCamunda-1.war
├── META-INF/
│   └── MANIFEST.MF
└── WEB-INF/
    ├── web.xml
    └── classes/
        ├── tn/insat/urb/tp3/
        │   └── HelloworldApplication.class
        ├── META-INF/
        │   └── processes.xml
        └── helloworld.bpmn
```

## Technologies Utilisées

- **Camunda BPM** : 7.18.0
- **Java** : 8
- **Maven** : 3.x
- **Servlet API** : 3.1.0

## Notes Importantes

- L'application utilise l'annotation `@ProcessApplication` pour la découverte automatique
- Le fichier `web.xml` est minimal car Camunda découvre automatiquement les ProcessApplications
- Les dépendances Camunda sont en scope `provided` car elles sont fournies par le serveur Camunda
- Le processus est configuré pour ne pas être supprimé lors du déploiement (`isDeleteUponUndeploy=false`)

## Auteur

Projet développé dans le cadre du cours TP3 - INSAT URB

## Licence

Ce projet est fourni à des fins éducatives.

