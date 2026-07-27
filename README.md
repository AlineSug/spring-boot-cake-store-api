*****************Spring Boot Cake Store API*****************

This project is a RESTful API built with Java and Spring Boot to strengthen my backend development skills and
deepen my understanding of REST API design and security best practices.
The application uses Spring Data JPA with MySQL for data persistence and 
follows a layered architecture (Controller, Service, Repository, and DTO) to improve maintainability, scalability, and separation of concerns.
RBAC implemented on top of JWT authentication. 
USER has read-only access (GET only), ADMIN has full access.
New registrations are always created as USER (preventing privilege escalation), and an ADMIN can promote other users.

--------------*Features*----------------

-User registration and authentication

-Password encryption using BCrypt

-JWT (JSON Web Token) authentication for stateless sessions

-Token expiration configured for enhanced security

-Protected endpoints that require a valid JWT for access

-Public access only to authentication and user registration endpoints

-Full CRUD operations for cake management

-RBAC Role Control Access


----------------Cake Management-----------------

Authenticated users can:

Create new cakes

View all registered cakes

Update cake information

Delete existing cakes

Each cake contains the following attributes:

Id Auto_incremented

Name
Flavor
Type
Price



-------------*Technologies Used*-----------------

Java 17

Spring Boot


Spring Security

JWT Authentication

Spring Data JPA

MySQL

BCrypt

Maven


This project is intended for learning purposes and is being built step by step
as I continue improving my backend development skills.
New features, security enhancements, refactoring, and best practices will be implemented
throughout the development process.

プロジェクトについて

学習した知識を定着させるためには、実際に手を動かして開発することが大切だと考えています。
そこで今回、Java と Spring Boot を使用して、Cake Store REST API を開発しました。

-------*使用技術*---------

Java 17

Spring Boot

Spring Security

JWT認証

Spring Data JPA

MySQL

BCrypt


-----------*実装した機能*------------------------------

・ユーザー登録およびログイン機能

・JWTによる認証・認可機能

・BCryptによるパスワードの暗号化

・ケーキ情報のCRUD機能（登録・取得・更新・削除）

・認証が必要なエンドポイントの保護

. RBAC実装（ADMINとUSER)

ログインとユーザー登録以外のAPIは、JWTトークンによる認証が必要な構成にしています。
また、Controller、Service、Repository、DTOのレイヤードアーキテクチャを採用し、
保守性や拡張性を意識した設計を行いました。
このプロジェクトを通して、REST API開発、データベース連携、認証・認可の仕組み、
JWT認証にRBACを実装。USERは読み取り専用(GETのみ)、ADMINは全操作可能。新規登録者は常にUSERとして作成され(権限昇格防止)、ADMINが他ユーザーを昇格できます。
そしてバックエンド開発の実践的なスキルを学ぶことができました。
現在も機能追加や改善を続けながら、Javaバックエンドエンジニアとして成長できるよう学習を続けています。


本プロジェクトは学習目的で開発しており、バックエンド開発スキルの向上に合わせて段階的に機能を追加しています。
今後もセキュリティ強化、新機能の追加、リファクタリング、およびベストプラクティスの適用を継続して行う予定です。
