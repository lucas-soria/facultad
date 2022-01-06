describe('Visita pagina univerisdad', () => {
    beforeEach(() => {
      cy.visit('https://um.edu.ar')
    });

    it('Looks for internos', () => {
        cy.get('.fl-button-text a').contains("Internos")
      });
});
