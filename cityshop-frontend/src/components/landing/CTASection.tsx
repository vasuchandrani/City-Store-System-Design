import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';
import { ArrowRight, Store, Truck, Users } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';

const roles = [
  {
    icon: Users,
    title: 'Shop as a Customer',
    description: 'Browse local shops, order from multiple stores, and get fast delivery.',
    cta: 'Start Shopping',
    link: '/auth?mode=signup&role=customer',
    color: 'brand',
  },
  {
    icon: Store,
    title: 'Register Your Shop',
    description: 'Take your local business online and reach more customers in your city.',
    cta: 'Join as Shopkeeper',
    link: '/auth?mode=signup&role=shopkeeper',
    color: 'teal',
  },
  {
    icon: Truck,
    title: 'Become a Delivery Partner',
    description: 'Earn money delivering orders from local shops with flexible hours.',
    cta: 'Start Earning',
    link: '/auth?mode=signup&role=delivery_partner',
    color: 'success',
  },
];

export function CTASection() {
  return (
    <section className="py-20 md:py-32">
      <div className="container mx-auto px-4">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="text-center mb-16"
        >
          <h2 className="text-3xl md:text-4xl font-bold mb-4">
            Join the CityShop Community
          </h2>
          <p className="text-lg text-muted-foreground max-w-2xl mx-auto">
            Choose how you want to be part of the local commerce revolution.
          </p>
        </motion.div>

        <div className="grid md:grid-cols-3 gap-8">
          {roles.map((role, index) => {
            const Icon = role.icon;
            return (
              <motion.div
                key={role.title}
                initial={{ opacity: 0, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                viewport={{ once: true }}
                transition={{ delay: index * 0.15 }}
              >
                <Card variant="elevated" className="h-full">
                  <CardContent className="p-8 flex flex-col items-center text-center h-full">
                    <div className={`w-16 h-16 rounded-2xl flex items-center justify-center mb-6 ${
                      role.color === 'brand' ? 'bg-brand-light' :
                      role.color === 'teal' ? 'bg-accent-teal/10' : 'bg-success-light'
                    }`}>
                      <Icon className={`w-8 h-8 ${
                        role.color === 'brand' ? 'text-brand' :
                        role.color === 'teal' ? 'text-accent-teal' : 'text-success'
                      }`} />
                    </div>
                    <h3 className="font-bold text-xl mb-3">{role.title}</h3>
                    <p className="text-muted-foreground mb-6 flex-grow">{role.description}</p>
                    <Link to={role.link} className="w-full">
                      <Button 
                        variant={role.color === 'brand' ? 'hero' : role.color === 'teal' ? 'teal' : 'success'}
                        size="lg"
                        className="w-full"
                      >
                        {role.cta}
                        <ArrowRight className="w-4 h-4" />
                      </Button>
                    </Link>
                  </CardContent>
                </Card>
              </motion.div>
            );
          })}
        </div>

        {/* Bottom CTA Banner */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="mt-20 bg-gradient-brand rounded-3xl p-8 md:p-12 text-center text-white"
        >
          <h2 className="text-2xl md:text-3xl font-bold mb-4">
            Ready to Transform Local Commerce?
          </h2>
          <p className="text-white/80 mb-8 max-w-xl mx-auto">
            Join thousands of customers, shopkeepers, and delivery partners 
            who are already part of the CityShop community.
          </p>
          <Link to="/auth?mode=signup">
            <Button 
              variant="outline" 
              size="xl"
              className="bg-white text-brand border-white hover:bg-white/90 hover:text-brand"
            >
              Get Started Now
              <ArrowRight className="w-5 h-5" />
            </Button>
          </Link>
        </motion.div>
      </div>
    </section>
  );
}
