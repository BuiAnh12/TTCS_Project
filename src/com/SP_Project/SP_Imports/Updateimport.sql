CREATE PROCEDURE UpdateImport
    @ImportId INT,
    @ProductId INT,
    @ManufacturingDate DATE,
    @ExpiryDate DATE,
    @ImportDate DATE,
    @ImportQuantity INT,
    @AvailableQuantity INT,
    @UnitPrice DECIMAL(10,2)
AS
BEGIN
	SET NOCOUNT ON
    UPDATE Imports
    SET ProductId = @ProductId,
        ManufacturingDate = @ManufacturingDate,
        ExpiryDate = @ExpiryDate,
        ImportDate = @ImportDate,
        ImportQuantity = @ImportQuantity,
        AvailableQuantity = @AvailableQuantity,
        UnitPrice = @UnitPrice
    WHERE ImportId = @ImportId;
END;