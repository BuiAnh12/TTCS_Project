CREATE PROCEDURE FindAvailableImports
    @ProductId INT,
    @Quantity INT
AS
BEGIN
    SET NOCOUNT ON;

    WITH t1 AS (
        SELECT TOP 1 i1.ImportId
        FROM Imports i1
        JOIN Imports i2 ON i1.ProductId = i2.ProductId AND i1.ExpiryDate >= i2.ExpiryDate
        WHERE i1.ProductId = @ProductId AND i1.AvailableQuantity > 0
        GROUP BY i1.ImportId
        HAVING SUM(i2.AvailableQuantity) >= @Quantity
    )
    SELECT *
    FROM Imports
    WHERE ProductId = @ProductId AND AvailableQuantity > 0
        AND ExpiryDate <= (
            SELECT ExpiryDate
            FROM Imports
            WHERE ImportId = (SELECT ImportId FROM t1)
        )
    ORDER BY ExpiryDate ASC;
END