CREATE PROCEDURE addProduct
    @ProductName NVARCHAR(255),
    @Manufacturer NVARCHAR(255),
    @Description NVARCHAR(255),
    @Category NVARCHAR(255),
    @SellPrice DECIMAL(10, 2)
AS
BEGIN
	SET NOCOUNT ON
    INSERT INTO Products (ProductName, Manufacturer, Description, Category, SellPrice)
    VALUES (@ProductName, @Manufacturer, @Description, @Category, @SellPrice);
END;