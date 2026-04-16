## Spring Boot 幣別匯率整合作業 (Coindesk API)
這是一個基於 Spring Boot 2.7 (JDK 8) 開發的後端面試作業。主要功能包含幣別資料的 CRUD 維護，以及整合外部 Coindesk API 進行資料轉換與呈現。
## 開發環境
- Java 8
- Spring Boot
- H2 Database
- Maven

## 啟動方式
1. 在專案根目錄執行：mvn spring-boot:run
2. H2 Console：
- 路徑：http://localhost:8080/h2-console
- User Name：sa / Password：(留空)

## API清單
### 幣別 CRUD
- GET /api/currencies/list
- GET /api/currencies/{code}
- POST /api/currencies
- PUT /api/currencies
- DELETE /api/currencies/{code}
POST / PUT Request Body：
{
  "code": "USD",
  "chineseName": "美元"
}

### Coindesk
- GET /api/coindesk/raw
- GET /api/coindesk/transformed

## 測試說明
執行 mvn test 即可跑完所有測試，包含：
1. 資料轉換單元測試
2. 幣別 CRUD API測試
3. 呼叫Coindesk API 與轉換的整合測試

## 設計說明
- 分層架構（Controller / Service / Repository）
- 使用 DTO 進行資料傳輸
- 實作全域 Exception Handler，統一錯誤回應
- Request Body使用Validation進行參數驗證

