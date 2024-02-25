# Item Management Service

Detta projekt är en Spring Boot-applikation som erbjuder en enkel CRUD (Create, Read, Update, Delete) funktionalitet för att hantera items. Applikationen är konfigurerad med en kontinuerlig integrations- och leveranspipeline (CI/CD) som använder GitHub Actions, AWS CodePipeline, och CodeBuild, samt är deployad på AWS Elastic Beanstalk.

## Funktioner

- **getAllItems**: En GET endpoint som tillåter användare att hämta alla items som finns lagrade i MongoDB-databasen. Denna funktion är tillgänglig via den publika domänen.
- **createItem**: Skapar ett nytt item. Denna åtgärd utförs genom konsolapplikationen.
- **updateItem**: Uppdaterar ett befintligt item. Även denna åtgärd utförs genom konsolapplikationen.
- **deleteItem**: Tar bort ett specifikt item. Denna åtgärd är också bunden till konsolapplikationen.

## Teknikstack

- **Backend**: Java 17 maven Spring Boot
- **Databas**: MongoDB (Hostad online)
- **CI/CD**: GitHub Actions, AWS CodePipeline, CodeBuild
- **Hosting**: AWS Elastic Beanstalk

## CI/CD-process

Projektet använder en automatiserad CI/CD-pipeline för att säkerställa kvalitet och effektivitet i leveransen:

1. **GitHub Actions**: Automatiserar tester och kvalitetskontroller.
2. **AWS CodePipeline**: Hanterar automatiserad byggnad, testning och deployment processer.
3. **AWS CodeBuild**: Kompilerar koden och kör enhetstester.
4. **AWS Elastic Beanstalk**: Tar emot den byggda koden och deployar den till en skalbar webbservermiljö.

## Tillgång till Applikationen

Den deployade applikationen kan nås via den tilldelade AWS-domänen:

