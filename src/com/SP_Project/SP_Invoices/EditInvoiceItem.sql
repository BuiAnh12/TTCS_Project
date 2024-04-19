CREATE PROCEDURE EditInvoiceItem
    @InvoiceId INT,
    @ImportId INT,
    @UnitPrice DECIMAL,
    @Quantity INT,
    @TotalPrice DECIMAL,
    @Profit DECIMAL,
    @InvoiceItemId INT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Invoice_Items
    SET 
        InvoiceId = @InvoiceId,
        ImportId = @ImportId,
        UnitPrice = @UnitPrice,
        Quantity = @Quantity,
        TotalPrice = @TotalPrice,
        Profit = @Profit
    WHERE
        InvoiceItemId = @InvoiceItemId;
END
