CREATE PROCEDURE AddInvoiceItem
    @InvoiceId INT,
    @ImportId INT,
    @UnitPrice DECIMAL,
    @Quantity INT,
    @TotalPrice DECIMAL,
    @Profit DECIMAL
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Invoice_Items (InvoiceId, ImportId, UnitPrice, Quantity, TotalPrice, Profit) 
    VALUES (@InvoiceId, @ImportId, @UnitPrice, @Quantity, @TotalPrice, @Profit);
END