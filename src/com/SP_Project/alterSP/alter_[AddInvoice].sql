USE [DOAN_PHANBON]
GO
/****** Object:  StoredProcedure [dbo].[AddInvoice]    Script Date: 5/27/2024 9:47:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[AddInvoice]
    @CustomerId INT,
    @StaffId INT,
    @InvoiceId INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Invoices (CustomerId, StaffId, PurchaseDate, UpdateDate) 
    VALUES (@CustomerId, @StaffId, GETDATE(),GETDATE());

    SET @InvoiceId = SCOPE_IDENTITY();
END
