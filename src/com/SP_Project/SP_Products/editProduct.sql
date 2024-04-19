CREATE PROCEDURE editProduct
    @ProductId INT,
    @ProductName NVARCHAR(255),
    @Manufacturer NVARCHAR(255),
    @Description NVARCHAR(255),
    @Category NVARCHAR(255),
    @SellPrice DECIMAL(10, 2)
AS
BEGIN
SET NOCOUNT ON
    UPDATE Products
    SET ProductName = @ProductName,
        Manufacturer = @Manufacturer,
        Description = @Description,
        Category = @Category,
        SellPrice = @SellPrice
    WHERE ProductId = @ProductId;
END;
