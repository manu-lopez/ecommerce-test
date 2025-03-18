# ecommerce-test

## What it does

A REST API that retrieves the correct price for a product based on the date, product ID, and brand ID

## Problem solved

This API solves the challenge of retrieving the correct price when:
- Prices change over time
- Different brands have different pricing
- Multiple price rules might overlap

## Setup & Run

### Requirements
- Java 17+
- Maven 3.6+

### Quick start
```bash
# Clone the repository
git clone https://github.com/manu-lopez/ecommerce-test.git
cd ecommerce-test

# Build
mvn clean install

# Run
mvn spring-boot:run
```

The application runs on http://localhost:8080

## How to use

### API Endpoint

```
GET /api/price
```

### Query parameters
- `priceDate`: Date and time of query - e.g., `2020-06-14-16.00.00`
- `productId`: Product identifier - e.g., `35455`
- `brandId`: Brand identifier - e.g., `1`

### Example

Request:
```bash
curl -X GET "localhost:8080/api/price?priceDate=2020-06-14-18.15.00&productId=35455&brandId=1"
```

Response:
```json
{
    "currency": "EUR",
    "product_id": 35455,
    "brand_id": 1,
    "price_list": 2,
    "price_start_date": "2020-06-14T15:00:00",
    "price_end_date": "2020-06-14T18:30:00",
    "product_price": 25.45
}
```
