CREATE PROCEDURE InsertImport
    @ProductId INT,
    @ManufacturingDate DATE,
    @ExpiryDate DATE,
    @ImportDate DATE,
    @ImportQuantity INT,
    @AvailableQuantity INT,
    @UnitPrice DECIMAL(10,2)
AS
BEGIN
    INSERT INTO Imports (ProductId, ManufacturingDate, ExpiryDate, ImportDate, ImportQuantity, AvailableQuantity, UnitPrice)
    VALUES (@ProductId, @ManufacturingDate, @ExpiryDate, @ImportDate, @ImportQuantity, @AvailableQuantity, @UnitPrice);
END;