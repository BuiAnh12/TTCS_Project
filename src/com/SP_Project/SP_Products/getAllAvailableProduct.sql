CREATE PROCEDURE getAllAvailableProduct
AS
BEGIN
	SET NOCOUNT ON
    SELECT DISTINCT p.*, SUM(i.AvailableQuantity) AS totalAmount
    FROM Products p
    JOIN Imports i ON p.ProductId = i.ProductId
    WHERE i.AvailableQuantity > 0
    GROUP BY p.ProductId, p.Category, p.Description, p.Manufacturer, p.ProductName, p.Flag, p.SellPrice;
END;