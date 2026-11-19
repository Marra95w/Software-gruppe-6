import "../css/Nav.css"
import "../css/OmOss.css"
export default function OmOss() {
    return(
        <>
        <section className="intro-about">
            <h1>Om oss</h1>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in faucibus nunc. Aliquam erat volutpat. 
                Donec sapien dui, maximus id ornare nec, sollicitudin pellentesque enim. Curabitur accumsan orci ut hendrerit aliquet. 
                Cras laoreet gravida ante. Quisque ullamcorper, leo eu faucibus finibus, odio tortor bibendum eros, eu consectetur elit magna vitae erat. 
                Sed gravida, magna sit amet cursus ultrices, nulla elit aliquam odio, 
                in posuere odio eros ut tortor. Integer vestibulum rutrum varius. Fusce gravida tellus lectus, sit amet bibendum mi finibus at. 
                Pellentesque tristique purus sit amet justo rutrum, vel facilisis risus dignissim. Nulla rhoncus ultrices scelerisque. 
                In venenatis ipsum non orci ullamcorper dapibus.
            </p>
        </section>

         <div className="image-design">
                <img className="rose-design" src="../src/assets/bilde/rose.png" alt="rose" />
        </div>

        <section className="employees">
            <h2>Våre ansatte</h2>
            
            <article className="employee-card">
                <img className="employee-image" src="../src/assets/bilde/person.svg" alt="Person" />
               
               <section className="employee-info">
                    <h3>Geir Nordmann</h3>
                    <p>Redaktør</p>
                </section>
                <address className="employee-contact">
                    <a href="tel: 123 45 678">
                        <img src="../src/assets/bilde/phone.svg" alt="Telefon" />
                        123 45 678
                    </a>
                
                    <a href="mailto: geinor@ostfoldhusflid.no">
                        <img src="../src/assets/bilde/e-post.svg" alt="E-post" />
                        geinor@ostfoldhusflid.no
                    </a>
                </address>
            </article>
        </section>
        </>
    )
}