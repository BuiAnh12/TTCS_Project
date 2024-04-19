CREATE PROCEDURE AddInvoice
    @CustomerId INT,
    @StaffId INT,
    @PurchaseDate DATE,
    @InvoiceId INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Invoices (CustomerId, StaffId, PurchaseDate) 
    VALUES (@CustomerId, @StaffId, @PurchaseDate);

    SET @InvoiceId = SCOPE_IDENTITY();
END