CREATE PROCEDURE getAllProduct
    @status INT,
    @searchKeyword NVARCHAR(255)
AS
BEGIN
    IF @status = 1
    BEGIN
        SELECT * FROM Products 
        WHERE ProductName LIKE '%' + @searchKeyword + '%' 
        ORDER BY ProductName;
    END
    ELSE IF @status = 2
    BEGIN
        SELECT * FROM Products 
        WHERE ProductName LIKE '%' + @searchKeyword + '%' 
        ORDER BY Manufacturer;
    END
    ELSE IF @status = 3
    BEGIN
        SELECT * FROM Products 
        WHERE ProductName LIKE '%' + @searchKeyword + '%' 
        ORDER BY Category;
    END
END;	