CREATE PROCEDURE EditInvoice
    @CustomerId INT,
    @StaffId INT,
    @PurchaseDate DATE,
    @InvoiceId INT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Invoices
    SET 
        CustomerId = @CustomerId,
        StaffId = @StaffId,
        PurchaseDate = @PurchaseDate
    WHERE
        InvoiceId = @InvoiceId;
END